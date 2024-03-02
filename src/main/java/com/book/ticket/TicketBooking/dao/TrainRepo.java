package com.book.ticket.TicketBooking.dao;

import com.book.ticket.TicketBooking.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRepo extends JpaRepository<Train, Integer> {

    Optional<Train> findByDepartureAndArrival(String departure, String arrival);

    Optional<Train> findByName(String name);

    Optional<Train> findByTrainId(Long trainId);
}
