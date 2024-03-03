package com.book.ticket.TicketBooking.controller;

import com.book.ticket.TicketBooking.model.Passenger;
import com.book.ticket.TicketBooking.service.PassengerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PassengerController.class)
class PassengerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PassengerService passengerService;
    Passenger passenger = null;


    @BeforeEach
    void setUp() {
        passenger=new Passenger("Akshay", "akshay@gmail.com", "1234567890", "Delhi");
        passenger.setId(1L);
    }

    @AfterEach
    void tearDown() {
        passenger=null;
        mockMvc=null;
    }

    @Test
    void registerPassenger() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(passenger);

        when(passengerService.registerPassenger(passenger))
                .thenReturn(String.valueOf(new ResponseEntity<>("registered", HttpStatus.OK)));

        this.mockMvc.perform(post("/passenger/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(response))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getPassengers() throws Exception{
        List<Passenger> passengers = List.of(passenger);
        when(passengerService.getAllPassengers())
                .thenReturn(new ResponseEntity<>(passengers, HttpStatus.OK));

        this.mockMvc.perform(get("/passenger/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].name").value("Akshay"));
    }

    @Test
    void getPassengerById() throws Exception {
        when(passengerService.getPassengerById(1L))
                .thenReturn(new ResponseEntity<>(passenger, HttpStatus.OK));

        this.mockMvc.perform(get("/passenger/id/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Akshay"));
    }

    @Test
    void getPassengerByName() throws Exception {
        when(passengerService.getPassengerByName("Akshay"))
                .thenReturn(new ResponseEntity<>(passenger, HttpStatus.OK));

        this.mockMvc.perform(get("/passenger/name/Akshay"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Akshay"))
                .andDo(print());

    }

    @Test
    void updatePassenger() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(passenger);

        when(passengerService.updatePassenger(passenger))
                .thenReturn(new ResponseEntity<>("Record updated", HttpStatus.OK));

        this.mockMvc.perform(put("/passenger/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(response))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deletePassenger() throws Exception {
        when(passengerService.deleteById(1L))
                .thenReturn(new ResponseEntity<>("Record deleted", HttpStatus.OK));

        this.mockMvc.perform(delete("/passenger/delete/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Record deleted"));
    }
}