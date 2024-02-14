package com.book.ticket.TicketBooking.service;

import com.book.ticket.TicketBooking.dao.TrainRepo;
import com.book.ticket.TicketBooking.exception.TrainNotFoundException;
import com.book.ticket.TicketBooking.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    @Autowired
    private TrainRepo trainRepo;

    public ResponseEntity<String> addTrain(Train train){
        // adding train
        trainRepo.save(train);
        return new ResponseEntity<>("train added successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<Train>> getAllTrains() {
        // fetching all available data
        List<Train> trains = trainRepo.findAll();
        if(!trains.isEmpty()){
            // show all details
            return new ResponseEntity<>(trainRepo.findAll(), HttpStatus.OK);
        } else{
            // not found any records
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Train> getTrainByName(String name) {
        // fetch details by name
        Train train = trainRepo.getByName(name);
        if(train != null){
            // train object
            return new ResponseEntity<>(train, HttpStatus.OK);
        } else{
            // not found
            throw new TrainNotFoundException("Not Found with name " + name);
        }
    }

    public ResponseEntity<Train> getDetailsById(Integer id) {
        // fetch details by id
        Optional<Train> optional = trainRepo.findById(id);

        if(optional.isPresent()){
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else{
            throw new TrainNotFoundException("Not Found with id " + id);
        }
    }


    public ResponseEntity<Train> getByDepartureAndArrival(String departure, String arrival) {
        Optional<Train> optional = trainRepo.findByDepartureAndArrival(departure, arrival);

        if(optional.isPresent()){
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else{
            throw new TrainNotFoundException("Not found");
        }
    }

    public ResponseEntity<Train> getTrainByTrainId(Long trainId) {
        // find train by train id
        Optional<Train> optional = trainRepo.findByTrainId(trainId);
        if(optional.isPresent())
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        else
            throw new TrainNotFoundException("Not Found");

    }
}
