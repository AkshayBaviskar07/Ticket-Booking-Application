package com.book.ticket.TicketBooking.dao;

import com.book.ticket.TicketBooking.model.Passenger;
import com.book.ticket.TicketBooking.model.Ticket;
import com.book.ticket.TicketBooking.model.Train;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TicketRepoTest {
    @Mock
    private TicketRepo ticketRepo;
    Ticket ticket = null;
    Passenger passenger = null;
    Train train = null;
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
        ticket = null;
        passenger = null;
        train = null;
    }

    @Test
    void test_FindByTicketId() {
        when(ticketRepo.findByTicketId(ticket.getTicketId()))
                .thenReturn(Optional.of(ticket));

        assertEquals(ticket, ticketRepo.findByTicketId(ticket.getTicketId()).get());
    }
}