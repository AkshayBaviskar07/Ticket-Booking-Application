package com.book.ticket.TicketBooking.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "train_id")
    private Long trainId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String arrival;

    @Column(name = "travel_amount", nullable = false)
    private Integer travelAmount;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket>tickets;

    public Train() {
    }

    public Train(String name, String departure, String arrival) {
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getTravelAmount() {
        return travelAmount;
    }

    public void setTravelAmount(Integer travelAmount) {
        this.travelAmount = travelAmount;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", trainId=" + trainId +
                ", name='" + name + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", travelAmount=" + travelAmount +
                ", tickets=" + tickets +
                '}';
    }
}
