package com.book.ticket.TicketBooking.controller;

import com.book.ticket.TicketBooking.model.Passenger;
import com.book.ticket.TicketBooking.model.Ticket;
import com.book.ticket.TicketBooking.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("passenger")
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
    @PostMapping("register")
    public ResponseEntity<String> registerPassenger(@RequestBody Passenger passenger){
        // enroll passenger
        return new ResponseEntity<>(passengerService.registerPassenger(passenger), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<Passenger>> getPassengers(){
        // fetch all available details
        return passengerService.getAllPassengers();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id){
        // fetch by id
        return passengerService.getPassengerById(id);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Passenger> getPassengerByName(@PathVariable String name){
        // fetch by name
        return passengerService.getPassengerByName(name);
    }

    @PutMapping("update")
    public ResponseEntity<String> updatePassenger(@RequestBody Passenger passenger){
        // update passenger
        return passengerService.updatePassenger(passenger);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable Long id){
        return passengerService.deleteById(id);
    }

}
