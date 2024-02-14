package com.book.ticket.TicketBooking.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.book.ticket.TicketBooking.utility.Constants.*;

@Component
@Aspect
public class TicketLoggingAspects {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketLoggingAspects.class);

    //    Aspects for controllers

    @Before("execution(* com.book.ticket.TicketBooking.controller.TicketController.*(..))")
    public void logCallBeforeController(JoinPoint point){
        LOGGER.info(CYAN + "Controller Before : "+point.getSignature().getName() + RESET);
    }

    @After("execution(* com.book.ticket.TicketBooking.controller.TicketController.*(..))")
    public void logCallAfterController(JoinPoint point){
        LOGGER.info(PURPLE + "Controller After : "+point.getSignature().getName() + RESET);
    }

    @AfterThrowing("execution(* com.book.ticket.TicketBooking.controller.TicketController.*(..))")
    public void logCallAfterThrowingExceptionInController(JoinPoint point){
        LOGGER.warn(RED + "Controller After Exception : "+point.getSignature().getName() + RESET);
    }

    @AfterReturning("execution(* com.book.ticket.TicketBooking.controller.TicketController.*(..))")
    public void logCallAfterReturningController(JoinPoint point){
        LOGGER.info(GREEN + "Controller After Returning: "+point.getSignature().getName() + RESET);
    }


//    Aspects for service

    @Before("execution(* com.book.ticket.TicketBooking.service.TicketService.*(..))")
    public void logCallBeforeService(JoinPoint point){
        LOGGER.info(YELLOW + "Service Before : "+point.getSignature().getName() + RESET);
    }

    @After("execution(* com.book.ticket.TicketBooking.service.TicketService.*(..))")
    public void logCallAfterService(JoinPoint point){
        LOGGER.info(PURPLE + "Service After : "+point.getSignature().getName() + RESET);
    }

    @AfterReturning("execution(* com.book.ticket.TicketBooking.service.TicketService.*(..))")
    public void logCallAfterReturningService(JoinPoint point){
        LOGGER.info(GREEN + "Service After Returning: "+point.getSignature().getName() + RESET);
    }

    @AfterThrowing("execution(* com.book.ticket.TicketBooking.service.TicketService.*(..))")
    public void logCallAfterThrowingExceptionInService(JoinPoint point){
        LOGGER.warn(RED + "Service After Exception: "+point.getSignature().getName() + RESET);
    }
}
