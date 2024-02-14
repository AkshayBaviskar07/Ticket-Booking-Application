package com.book.ticket.TicketBooking.dao;

import com.book.ticket.TicketBooking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {

    Optional<Ticket> findByTicketId(Long ticketId);
}
