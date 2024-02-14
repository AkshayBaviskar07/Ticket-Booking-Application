package com.book.ticket.TicketBooking.controller;

import com.book.ticket.TicketBooking.model.Train;
import com.book.ticket.TicketBooking.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("add")
    public ResponseEntity<String> addTrain(@RequestBody Train train){
        // add train details
        return trainService.addTrain(train);
    }

    @GetMapping("all")
    public ResponseEntity<List<Train>> getAllTrains(){
        // get all details
        return trainService.getAllTrains();
    }

    public ResponseEntity<Train> getTrainByTrainId(@PathVariable Long trainId){
        // get details by train id
        return trainService.getTrainByTrainId(trainId);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Train> getByName(@PathVariable String name){
        // get details by train name
        return trainService.getTrainByName(name);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Train> getById(@PathVariable Integer id){
        // get details by id
        return trainService.getDetailsById(id);
    }

    @GetMapping("station/{departure}/{arrival}")
    public ResponseEntity<Train> getByDepartureAndArrival(@PathVariable String departure,
                                                          @PathVariable String arrival){
        // get details by departure and arrival
        return trainService.getByDepartureAndArrival(departure, arrival);
    }
}
