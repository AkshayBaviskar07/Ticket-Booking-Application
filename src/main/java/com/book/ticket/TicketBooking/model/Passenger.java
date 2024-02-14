package com.book.ticket.TicketBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String phone;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    public Passenger() {super();}

    public Passenger(String name, String email, String phone, String city) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
    }

    public Passenger(String name, String email, String phone, String city, Set<Ticket> tickets) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
