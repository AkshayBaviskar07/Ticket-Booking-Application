package com.book.ticket.TicketBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;

    @Column(name = "book_date", nullable = false)
    private LocalDate date;

    @Column(name = "departure_station", nullable = false)
    private String departure;

    @Column(name = "arrival_station", nullable = false)
    private String arrival;

    @Column(name = "total_tickets", nullable = false)
    private Integer totalTickets;

    @Column(name = "total_price", nullable = false)
    private Integer totalAmount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    public Ticket() {
    }

    public Ticket(Integer id,
                  LocalDate date,
                  String departure,
                  String arrival,
                  Integer totalTickets,
                  Integer totalAmount) {
        this.id = id;
        this.date = date;
        this.departure = departure;
        this.arrival = arrival;
        this.totalTickets = totalTickets;
        this.totalAmount = totalAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Integer totalTickets) {
        this.totalTickets = totalTickets;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", date=" + date +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", totalTickets=" + totalTickets +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
