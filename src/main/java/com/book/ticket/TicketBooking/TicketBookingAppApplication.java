package com.book.ticket.TicketBooking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.Servers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
	title = "Ticket Booking App",
	version = "1.0",
	description = "Ticket Booking Rest App"
    ),
	servers = @Server(
		url = "http://localhost:8484",
		description = "Ticket Booking Server"
	)
)
public class TicketBookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingAppApplication.class, args);
	}

}
