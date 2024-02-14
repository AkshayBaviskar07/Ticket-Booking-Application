package com.book.ticket.TicketBooking.controller;

import com.book.ticket.TicketBooking.model.Passenger;
import com.book.ticket.TicketBooking.model.Ticket;
import com.book.ticket.TicketBooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Book tickets for passengers
    @PutMapping("book/{trainName}/{totalTickets}")
    public ResponseEntity<String> bookTickets(@RequestBody Passenger passenger,
                                              @PathVariable String trainName,
                                              @PathVariable Integer totalTickets){

        // book tickets
        return ticketService.bookTickets(passenger, trainName, totalTickets);
    }

    @DeleteMapping("cancel/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable Long ticketId){
        // cancel tickets
        return ticketService.cancelTicket(ticketId);
    }


    @GetMapping("all")
    public ResponseEntity<List<Ticket>> getAllTickets(){
        // get all tickets
        return ticketService.getAllTickets();
    }
}
