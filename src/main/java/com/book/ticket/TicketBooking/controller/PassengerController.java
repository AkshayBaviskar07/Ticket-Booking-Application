package com.book.ticket.TicketBooking.controller;

import com.book.ticket.TicketBooking.model.Passenger;
import com.book.ticket.TicketBooking.model.Ticket;
import com.book.ticket.TicketBooking.service.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("passenger")
@Tag(name = "Passenger", description = "Operations on passenger entity")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    /*
    *   CRUD OPERATIONS for passengers
    *   getAllPassengers
    *   getPassengerById
    *   getPassengerByName
    *   registerPassenger
    *   updatePassenger
    *   deletePassenger
    */
    @Operation(summary = "POST Operation", description = "Register a new passenger")
    @PostMapping("register")
    public ResponseEntity<String> registerPassenger(@RequestBody Passenger passenger){
        // enroll passenger
        return new ResponseEntity<>(passengerService.registerPassenger(passenger), HttpStatus.OK);
    }

    @Operation(summary = "GET Operation", description = "Fetch all passengers")
    @GetMapping("all")
    public ResponseEntity<List<Passenger>> getPassengers(){
        // fetch all available details
        return passengerService.getAllPassengers();
    }

    @Operation(summary = "GET Operation", description = "Fetch passenger by id")
    @GetMapping("id/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id){
        // fetch by id
        return passengerService.getPassengerById(id);
    }

    @Operation(summary = "GET Operation", description = "Fetch passenger by name")
    @GetMapping("name/{name}")
    public ResponseEntity<Passenger> getPassengerByName(@PathVariable String name){
        // fetch by name
        return passengerService.getPassengerByName(name);
    }

    @Operation(summary = "PUT Operation", description = "Update passenger information")
    @PutMapping("update")
    public ResponseEntity<String> updatePassenger(@RequestBody Passenger passenger){
        // update passenger
        return passengerService.updatePassenger(passenger);
    }

    @Operation(summary = "DELETE Operation", description = "Delete passenger by id")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable Long id){
        return passengerService.deleteById(id);
    }

}
