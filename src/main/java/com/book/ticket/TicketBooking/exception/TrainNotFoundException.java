package com.book.ticket.TicketBooking.exception;

public class TrainNotFoundException extends RuntimeException {
    public TrainNotFoundException(String message) {
        super(message);
    }
}
