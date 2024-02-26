package com.book.ticket.TicketBooking.controller;

import com.book.ticket.TicketBooking.model.Train;
import com.book.ticket.TicketBooking.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("train")
@Tag(name = "Train", description = "Operations on train entity")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("add")
    @Operation(summary = "POST Operation", description = "Add a new train")
    public ResponseEntity<String> addTrain(@RequestBody Train train){
        // add train details
        return trainService.addTrain(train);
    }

    @GetMapping("all")
    public ResponseEntity<List<Train>> getAllTrains(){
        // get all details
        return trainService.getAllTrains();
    }

    @Operation(summary = "GET Operation", description = "Fetch trains by trainId")
    @GetMapping("train-id/{trainId}")
    public ResponseEntity<Train> getTrainByTrainId(@PathVariable Long trainId){
        // get details by train id
        return trainService.getTrainByTrainId(trainId);
    }

    @Operation(summary = "GET mapping", description = "Fetch train by name")
    @GetMapping("name/{name}")
    public ResponseEntity<Train> getByName(@PathVariable String name){
        // get details by train name
        return trainService.getTrainByName(name);
    }

    @Operation(summary = "GET Operation", description = "Fetch trains by id")
    @GetMapping("id/{id}")
    public ResponseEntity<Train> getById(@PathVariable Integer id){
        // get details by id
        return trainService.getDetailsById(id);
    }

    @Operation(summary = "GET Operation", description = "Fetch trains by arrival and departure station")
    @GetMapping("station/{departure}/{arrival}")
    public ResponseEntity<Train> getByDepartureAndArrival(@PathVariable String departure,
                                                          @PathVariable String arrival){
        // get details by departure and arrival
        return trainService.getByDepartureAndArrival(departure, arrival);
    }
}
