package com.book.ticket.TicketBooking.service;

import com.book.ticket.TicketBooking.dao.TrainRepo;
import com.book.ticket.TicketBooking.exception.TrainNotFoundException;
import com.book.ticket.TicketBooking.model.Train;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrainServiceTest {

    @Autowired
    private TrainService service;
    @MockBean
    private TrainRepo repo;

    Train train = null;


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
        train = null;
    }

    @Test
    void shouldAddTrain() {
        when(repo.save(train)).thenReturn(train);
        ResponseEntity<String> response = service.addTrain(train);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("train added successfully", response.getBody());
    }

    /*
    * Test case for get all trains
    * */
    @Test
    void shouldGetAllTrains() {
        // Arrange
        when(repo.findAll()).thenReturn(List.of(train));
        // Act
        ResponseEntity<List<Train>> response = service.getAllTrains();
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void shouldThrowExceptionWhenGetAllTrains(){
        when(repo.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Train>> response = service.getAllTrains();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody());
    }

    @Test
    void shouldGetTrainByName() {
        // Arrange
        when(repo.findByName("Duranto Express")).thenReturn(Optional.of(train));
        //Act
        ResponseEntity<Train> response = service.getTrainByName("Duranto Express");
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(train, response.getBody());
    }

    @Test
    void shouldThrowTrainNotFoundExceptionWhenGetTrainByName(){
        when(repo.findByName("Duranto Express")).thenThrow(new TrainNotFoundException("Not found"));
        assertThrows(TrainNotFoundException.class, ()->service.getTrainByName("Duranto Express"));
    }

    @Test
    void shouldGetDetailsById() {
        // Arrange
        when(repo.findById(1)).thenReturn(Optional.of(train));
        // Act
        ResponseEntity<Train> response = service.getDetailsById(1);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(train, response.getBody());
    }

    @Test
    void shouldThrowTrainNotFoundExceptionWhenGetDetailsById(){
        when(repo.findById(1)).thenThrow(new TrainNotFoundException("Not Found with id " + 1));
        assertThrows(TrainNotFoundException.class, ()->service.getDetailsById(1));
    }

    @Test
    void shouldGetByDepartureAndArrival() {
        when(repo.findByDepartureAndArrival(train.getDeparture(), train.getArrival()))
                .thenReturn(Optional.ofNullable(train));

        ResponseEntity<Train> response = service
                .getByDepartureAndArrival(train.getDeparture(), train.getArrival());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(train, response.getBody());
    }

    @Test
    void shouldThrowTrainNotFoundExceptionWhenGetDetailsByDepartureAndArrival(){
        when(repo.findByDepartureAndArrival(train.getDeparture(), train.getArrival()))
                .thenThrow(new TrainNotFoundException("Not found"));

        assertThrows(TrainNotFoundException.class,
                ()->service.getByDepartureAndArrival(train.getDeparture(), train.getArrival()));
    }

    @Test
    void shouldGetTrainByTrainId() {
        when(repo.findByTrainId(11009L)).thenReturn(Optional.ofNullable(train));

        ResponseEntity<Train> response = service.getTrainByTrainId(11009L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(train, response.getBody());
    }

    @Test
    void shouldThrowTrainNotFoundExceptionWhenGetDetailsByTrainId(){
        when(repo.findByTrainId(11009L)).thenThrow(new TrainNotFoundException("Not Found"));
        assertThrows(TrainNotFoundException.class, ()->service.getTrainByTrainId(11009L));
    }
}