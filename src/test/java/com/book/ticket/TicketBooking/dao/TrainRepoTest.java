package com.book.ticket.TicketBooking.dao;

import com.book.ticket.TicketBooking.model.Train;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrainRepoTest {
    @Mock
    private TrainRepo trainRepo;
    Train train=null;

    @BeforeEach
    void setUp() {
        train = new Train();
        train.setId(1);
        train.setTrainId(11009L);
        train.setName("Duranto Express");
        train.setArrival("Delhi");
        train.setDeparture("Mumbai");
        train.setAvailableSeats(20);
        train.setTravelAmount(1500);
    }

    @AfterEach
    void tearDown() {
        train=null;
    }

    @Test
    void test_findByDepartureAndArrival() {
        when(trainRepo.findByDepartureAndArrival("Delhi", "Mumbai"))
                .thenReturn(Optional.of(train));

        assertEquals(train, trainRepo
                .findByDepartureAndArrival("Delhi", "Mumbai").get());
    }

    @Test
    void test_findByName() {
        when(trainRepo.findByName("Duranto Express")).thenReturn(Optional.of(train));
        assertEquals(Optional.of(train), trainRepo.findByName("Duranto Express"));
    }

    @Test
    void test_findByTrainId() {
        when(trainRepo.findByTrainId(11009L)).thenReturn(Optional.of(train));
        assertEquals(Optional.of(train), trainRepo.findByTrainId(11009L));
    }
}