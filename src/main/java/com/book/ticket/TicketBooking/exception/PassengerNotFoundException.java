package com.book.ticket.TicketBooking.exception;

public class PassengerNotFoundException extends RuntimeException{
    public PassengerNotFoundException(String message) {
        super(message);
    }
}
