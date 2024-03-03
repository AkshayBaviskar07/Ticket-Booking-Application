package com.book.ticket.TicketBooking.controller;

import com.book.ticket.TicketBooking.model.Passenger;
import com.book.ticket.TicketBooking.model.Ticket;
import com.book.ticket.TicketBooking.model.Train;
import com.book.ticket.TicketBooking.service.TicketService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TicketService service;
    @Autowired
    private TicketController ticketController;
    Train train=null;
    Passenger passenger=null;
    Ticket ticket=null;
    HashSet<Ticket> tickets = new HashSet<>();

    @BeforeEach
    void setUp() {
        passenger=new Passenger();
        train=new Train();
        ticket=new Ticket();

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
        train=null;
        ticket=null;
        mockMvc=null;
        tickets = null;
    }

    @Test
    @JsonIgnore
    void bookTickets() throws Exception {

        when(service.bookTickets(passenger, "Duranto Express", 1))
                .thenReturn(new ResponseEntity<>("Tickets booked successfully", HttpStatus.OK));

        ResponseEntity<String> response = ticketController
                .bookTickets(passenger, "Duranto Express", 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void cancelTicket() {
        when(service.cancelTicket(1110L))
                .thenReturn(new ResponseEntity<>("ticket cancelled", HttpStatus.OK));

        ResponseEntity<String> response= ticketController.cancelTicket(1110L);

        assertEquals("ticket cancelled", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllTickets() throws Exception{
        when(service.getAllTickets())
                .thenReturn(new ResponseEntity<>(List.of(ticket), HttpStatus.OK));

        this.mockMvc.perform(get("/ticket/all"))
                .andExpect(status().isOk()).andDo(print());
    }
}