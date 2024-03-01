package com.book.ticket.TicketBooking.service;

import com.book.ticket.TicketBooking.dao.PassengerRepo;
import com.book.ticket.TicketBooking.exception.PassengerNotFoundException;
import com.book.ticket.TicketBooking.model.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PassengerServiceTest {

    @Autowired
    private PassengerService service;
    @MockBean
    private PassengerRepo repo;
    Passenger passenger1=null;
    Passenger passenger2=null;

    @BeforeEach
    void setUp() {
        passenger1=new Passenger("Akshay", "akshay@gmail.com", "1234567890", "Delhi");
        passenger1.setId(1L);
        passenger2=new Passenger("Sachin", "sachin@gmail.com", "1234567890", "Delhi");
        passenger2.setId(2L);
    }

    @AfterEach
    void tearDown() {
       passenger1=null;
       passenger2=null;
    }

    @Test
    void registerPassenger() {
        when(repo.save(passenger1)).thenReturn(passenger1);
        assertEquals("registered", service.registerPassenger(passenger1));
    }

    @Test
    void test_getAllPassengers() {
        when(repo.findAll()).thenReturn(List.of(passenger1, passenger2));
        assertEquals(2, Objects.requireNonNull(service.getAllPassengers().getBody()).size());
    }

    @Test
    void test_getAllPassengerNotFound(){
        when(repo.findAll()).thenThrow(new PassengerNotFoundException("Not found"));
        assertThrows(PassengerNotFoundException.class, ()->service.getAllPassengers());
    }

    @Test
    void test_getPassengerById() {
        when(repo.findById(1L)).thenReturn(Optional.of(passenger1));
        assertEquals("registered", service.registerPassenger(passenger1));
    }

    @Test
    void test_getPassengerByIdNotFound() {
        when(repo.findById(1L)).thenThrow(new PassengerNotFoundException("Not found"));
        assertThrows(PassengerNotFoundException.class, ()-> service.getPassengerById(1L));
    }

    @Test
    void test_getPassengerByName() {
        when(repo.findByName("Akshay")).thenReturn(Optional.of(passenger1));
        ResponseEntity<Passenger> response = service.getPassengerByName("Akshay");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passenger1, response.getBody());
    }

    @Test
    void test_getPassengerByNameNotFound(){
        when(repo.findByName("Rahul")).thenThrow(new PassengerNotFoundException("Not found"));
        assertThrows(PassengerNotFoundException.class, ()-> service.getPassengerByName("Rahul"));
    }

    @Test
    void test_updatePassenger() {
        when(repo.findById(1L)).thenReturn(Optional.of(passenger1));

        ResponseEntity<String> response = service.updatePassenger(passenger1);
        assertEquals("Record updated", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void test_updatePassengerNotFound() {
        when(repo.findById(1L)).thenThrow(new PassengerNotFoundException("Not found"));
        assertThrows(PassengerNotFoundException.class, ()->service.updatePassenger(passenger1));
    }

    @Test
    void deleteById() {
        when(repo.findById(1L)).thenReturn(Optional.of(passenger1));

        ResponseEntity<String> response = service.deleteById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Record deleted", response.getBody());
    }

    @Test
    void test_deleteByIdNotFound() {
        when(repo.findById(1L)).thenThrow(new PassengerNotFoundException("Not found"));
        assertThrows(PassengerNotFoundException.class, ()->service.deleteById(1L));
    }
}