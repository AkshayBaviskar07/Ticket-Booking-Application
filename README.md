# Ticket Booking - REST API platform

## Description
 > This project implements a robust and scalable REST API for booking tickets for
   various events or services. 
   This API provides a seamless and efficient way for users to browse available trains, select seats, and securely book tickets online.

## Key Features:

- **Flexible event support**: Create and manage events for diverse categories 
- **Efficient ticket management**: Offer different ticket types, handle availability, reservations, and confirmations.
- **Secure user profiles**: Enable user registration, login, and profile management for personalized booking experiences.
- **Comprehensive API documentation**: Provide clear and accurate API reference documentation for seamless integration.
- **Scalable**: Can scale for payment gateway using third party services, 
    book tickets for diverse events(concerts, movies, sports, etc.) or transportation(aeroplane, buses, cab).

## Prerequisites:

- Programming language: Java-8 or above
- Framework: SpringBoot
- Database server (e.g., MySQL or any relevant RDBMS)
- IntelliJ IDEA or Spring Tool Suite
- Basic understanding of REST APIs and HTTP protocols


## API Endpoints:

**Ticket:**
- `Put ticket/book/:trainName/:totalTickets`: Book ticket and update in passenger and train
- `DELETE ticket/cancel/:ticketId`: cancel a ticket by ticket id.

**Train:**
- `GET train/get`: Get a list of all trains.
- `GET train/id/:id`: Get details of a specific train.
- `POST train/add`: Create a new train.
- `PUT /events/:id`: Update an existing event.
- `DELETE /events/:id`: Delete a specific train.
- `GET train/station/:depature/:arrival`: Get train by departure and arrival station

**Passenger:**
- `GET passenger/all`: Get a list of all passengers.
- `GET passenger/id/:id`: Get details of a specific passenger.
- `GET passenger/name/:name`: Get details of a specific passenger by name.
- `POST passenger/register`: register passenger
- `PUT passenger/update`: Update an existing event.
- `DELETE passenger/delete/:id`: Delete details of a specific passenger

## Additional Notes:

- Error handling: Implement appropriate error handling and response codes for failed requests.
- Testing: Write Junit tests to ensure unit functionality and reliability. Api tested using `Postman`. 
 
## URL:

[LinkedIN](http://www.linkedin.com/in/akshay-baviskar-894931195) |
[HackerRank](https://www.hackerrank.com/profile/akshaybaviskar21)

