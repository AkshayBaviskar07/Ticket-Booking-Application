package com.book.ticket.TicketBooking.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TicketBookingAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleException(Exception exception) {
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
