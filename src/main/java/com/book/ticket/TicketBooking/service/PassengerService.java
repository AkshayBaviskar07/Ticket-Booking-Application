package com.book.ticket.TicketBooking.service;

import com.book.ticket.TicketBooking.dao.PassengerRepo;
import com.book.ticket.TicketBooking.dao.TrainRepo;
import com.book.ticket.TicketBooking.exception.PassengerNotFoundException;
import com.book.ticket.TicketBooking.exception.TrainNotFoundException;
import com.book.ticket.TicketBooking.model.Passenger;
import com.book.ticket.TicketBooking.model.Ticket;
import com.book.ticket.TicketBooking.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private TrainRepo trainRepo;

    public String registerPassenger(Passenger passenger) {

        //register with details
        passengerRepo.save(passenger);
        return "registered";
    }

    public ResponseEntity<List<Passenger>> getAllPassengers() {
        // fetch all available passengers details
        List<Passenger>passengers = passengerRepo.findAll();

        if(!passengers.isEmpty()){
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        } else{
            // data not available
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Passenger> getPassengerById(Long id) {
        Optional<Passenger> optional = passengerRepo.findById(id);

        if(optional.isPresent()){
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else{
            throw new PassengerNotFoundException("Not found");
        }
    }

    public ResponseEntity<Passenger> getPassengerByName(String name) {
        Optional<Passenger> optional = passengerRepo.findByName(name);
        if(optional.isPresent()){
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else{
            throw new PassengerNotFoundException("Not found");
        }
    }

    public ResponseEntity<String> updatePassenger(Passenger passenger) {
        Optional<Passenger> optional = passengerRepo.findById(passenger.getId());

        if(optional.isPresent()){
            passengerRepo.save(passenger);
            return new ResponseEntity<>("Record updated", HttpStatus.OK);
        } else{
            throw new PassengerNotFoundException("Not found.");
        }
    }

    public ResponseEntity<String> deleteById(Long id) {
        Optional<Passenger> optional = passengerRepo.findById(id);
        if (optional.isPresent()){
            passengerRepo.deleteById(id);
            return new ResponseEntity<>("Record deleted", HttpStatus.OK);
        } else{
            throw new PassengerNotFoundException("Not Found");

        }
    }
}
