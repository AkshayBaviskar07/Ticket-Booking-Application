package com.book.ticket.TicketBooking.dao;

import com.book.ticket.TicketBooking.model.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PassengerRepoTest {

    @Mock
    private PassengerRepo repo;
    Passenger passenger = null;
    @BeforeEach
    void setUp() {
        passenger=new Passenger("Akshay", "akshay@gmail.com", "1234567890", "Delhi");
        passenger.setId(1L);
    }

    @AfterEach
    void tearDown() {
        passenger=null;
    }

    @Test
    void findByName() {
        when(repo.findByName("Akshay"))
                .thenReturn(Optional.of(passenger));

        assertEquals(passenger, repo.findByName("Akshay").get());
    }
}