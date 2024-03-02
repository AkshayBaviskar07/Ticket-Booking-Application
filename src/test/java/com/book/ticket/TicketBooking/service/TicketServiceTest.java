package com.book.ticket.TicketBooking.service;

import com.book.ticket.TicketBooking.dao.TicketRepo;
import com.book.ticket.TicketBooking.dao.TrainRepo;
import com.book.ticket.TicketBooking.exception.TicketNotFoundException;
import com.book.ticket.TicketBooking.model.Passenger;
import com.book.ticket.TicketBooking.model.Ticket;
import com.book.ticket.TicketBooking.model.Train;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TicketServiceTest {

    Passenger passenger = null;
    Ticket ticket = null;
    Train train = null;
    LocalDate date = null;

    @Autowired
    private TicketService ticketService;
    @MockBean
    private TrainRepo trainRepo;
    @MockBean
    private TicketRepo ticketRepo;
    HashSet<Ticket> tickets = new HashSet<>();

    @BeforeEach
    void setUp() {
        passenger=new Passenger("Akshay", "akshay@gmail.com", "1234567890", "Delhi");
        passenger.setId(1L);

        train = new Train();
        train.setId(1);
        train.setTrainId(11009L);
        train.setName("Duranto Express");
        train.setArrival("Delhi");
        train.setDeparture("Mumbai");
        train.setAvailableSeats(20);
        train.setTravelAmount(1500);

        ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTrain(train);
        ticket.setTicketId(System.currentTimeMillis());
        ticket.setDate(LocalDate.now());
        ticket.setDeparture(train.getDeparture());
        ticket.setArrival(train.getArrival());
        ticket.setTotalAmount(train.getTravelAmount());
        ticket.setTotalTickets(1);

        tickets.add(ticket);
        passenger.setTickets(tickets);
        train.setTickets(tickets);
    }

    @AfterEach
    void tearDown() {
        passenger=null;
        ticket=null;
        train=null;
        tickets = null;
    }

    @Test
    void bookTickets() {
        Optional<Train> optionalTrain = Optional.of(train);
        when(trainRepo.findByName(any(String.class))).thenReturn(optionalTrain);

        when(ticketRepo.save(ticket)).thenReturn(ticket);

        ResponseEntity<String> response = ticketService
                .bookTickets(passenger, "Duranot Express", 3);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tickets booked successfully", response.getBody());
        assertFalse(tickets.isEmpty());
        assertDoesNotThrow(() -> ticketService.bookTickets(passenger, "Duranot Express", 3));
    }

    @Test
    void getAllTickets() {
        when(ticketRepo.findAll()).thenReturn(List.of(ticket));
        ResponseEntity<List<Ticket>> response = ticketService.getAllTickets();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void cancelTicket() {
        when(ticketRepo.findByTicketId(any(Long.class))).thenReturn(Optional.of(ticket));
        ResponseEntity<String> response = ticketService.cancelTicket(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ticket cancelled", response.getBody());
        assertDoesNotThrow(() -> ticketService.cancelTicket(1L));
    }
}