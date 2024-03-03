package com.book.ticket.TicketBooking.controller;

import com.book.ticket.TicketBooking.model.Train;
import com.book.ticket.TicketBooking.service.TrainService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainController.class)
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrainService service;
    Train train = null;
    List<Train> trains = new ArrayList<>();

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

        trains.add(train);
    }

    @AfterEach
    void tearDown() {
        train=null;
        trains=null;
    }

    @Test
    void addTrain() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(train);

        when(service.addTrain(train))
                .thenReturn(new ResponseEntity<>("train added successfully", HttpStatus.OK));

        this.mockMvc.perform(post("/train/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(response))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getAllTrains() throws Exception {
        when(service.getAllTrains())
                .thenReturn(new ResponseEntity<>(trains, HttpStatus.OK));

        this.mockMvc.perform(get("/train/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getTrainByTrainId() throws Exception {
        when(service.getTrainByTrainId(11009L))
                .thenReturn(new ResponseEntity<>(train, HttpStatus.OK));

        this.mockMvc.perform(get("/train/train-id/11009"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Duranto Express"));
    }

    @Test
    void shouldGetByName() throws Exception {
        when(service.getTrainByName("Duranto Express"))
                .thenReturn(new ResponseEntity<>(train, HttpStatus.OK));

        this.mockMvc.perform(get("/train/name/Duranto Express"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value("Duranto Express"));
    }

    @Test
    void getById() throws Exception {
        when(service.getDetailsById(1))
                .thenReturn(new ResponseEntity<>(train, HttpStatus.OK));

        this.mockMvc.perform(get("/train/id/1"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getByDepartureAndArrival() throws Exception {
        when(service.getByDepartureAndArrival("Delhi", "Mumbai"))
                .thenReturn(new ResponseEntity<>(train, HttpStatus.OK));

        this.mockMvc.perform(get("/train/station/Delhi/Mumbai"))
                .andExpect(status().isOk()).andDo(print());
    }
}