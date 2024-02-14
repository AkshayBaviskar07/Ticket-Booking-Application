package com.book.ticket.TicketBooking.exception;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String message){
        super(message);
    }
}
