package com.book.ticket.TicketBooking.service;

import com.book.ticket.TicketBooking.dao.PassengerRepo;
import com.book.ticket.TicketBooking.dao.TicketRepo;
import com.book.ticket.TicketBooking.dao.TrainRepo;
import com.book.ticket.TicketBooking.exception.TicketNotFoundException;
import com.book.ticket.TicketBooking.exception.TrainNotFoundException;
import com.book.ticket.TicketBooking.model.Passenger;
import com.book.ticket.TicketBooking.model.Ticket;
import com.book.ticket.TicketBooking.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private PassengerRepo passengerRepo;
    @Autowired
    private TrainRepo trainRepo;

    public ResponseEntity<String> bookTickets(Passenger passenger, String trainName, Integer totalTickets) {

        Optional<Train> optionalTrain = trainRepo.findByName(trainName);
        if(optionalTrain.isPresent()){
            // get train object
            Train train = optionalTrain.get();
            Set<Ticket> tickets = new HashSet<>();

            if(train.getAvailableSeats() >= totalTickets){

                for(int i=0; i<totalTickets; i++){
                    Ticket ticket = new Ticket();
                    ticket.setPassenger(passenger);
                    ticket.setTrain(train);
                    ticket.setTicketId(System.currentTimeMillis());
                    ticket.setDate(LocalDate.now());
                    ticket.setDeparture(train.getDeparture());
                    ticket.setArrival(train.getArrival());
                    ticket.setTotalTickets(totalTickets);
                    ticket.setTotalAmount(totalTickets * train.getTravelAmount());
                    ticketRepo.save(ticket);

                    tickets.add(ticket);
                }
                train.getTickets().addAll(tickets);
                train.setAvailableSeats(train.getAvailableSeats() - totalTickets);
                trainRepo.save(train);


                return new ResponseEntity<>("Tickets booked successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Tickets not available", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new TrainNotFoundException("Train not found");
        }
    }

    public ResponseEntity<List<Ticket>> getAllTickets() {
       if(ticketRepo.findAll().isEmpty()){
           return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
       } else{
           return new ResponseEntity<>(ticketRepo.findAll(), HttpStatus.OK);
       }
    }

    public ResponseEntity<String> cancelTicket(Long ticketId) {

        // get ticket by ticket id
        Optional<Ticket> optionalTicket = ticketRepo.findByTicketId(ticketId);
        if(optionalTicket.isPresent()){
            // get the ticket
            Ticket ticket = optionalTicket.get();

            //get train
            Train train = ticket.getTrain();
            train.setAvailableSeats(train.getAvailableSeats() + 1);
            trainRepo.save(train);

            //get passenger
            Passenger passenger = ticket.getPassenger();
            passenger.getTickets().remove(ticket);
            passengerRepo.save(passenger);

            //delete ticket
            ticketRepo.deleteById(ticket.getId());
            return new ResponseEntity<>("ticket cancelled", HttpStatus.OK);
        } else{
            throw new TicketNotFoundException("Ticket Not Found");
        }
    }
}
