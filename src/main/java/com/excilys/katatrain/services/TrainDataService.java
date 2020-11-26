package com.excilys.katatrain.services;

import com.excilys.katatrain.model.BookingReference;
import com.excilys.katatrain.model.Reservation;
import com.excilys.katatrain.model.Seat;
import com.excilys.katatrain.model.TrainSnapshot;
import com.excilys.katatrain.services.externals.TrainDataProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class TrainDataService {
    private final TrainDataProvider trainDataProvider;
    private final ObjectMapper objectMapper;

    @Autowired
    public TrainDataService(TrainDataProvider trainDataProvider, ObjectMapper objectMapper) {
        this.trainDataProvider = trainDataProvider;
        this.objectMapper = objectMapper;
    }

    public TrainSnapshot getTrainSnapshot(String trainId) {
        String json = this.trainDataProvider.getTrain(trainId);
        return adapt(trainId, json);
    }

    private TrainSnapshot adapt(String trainId, String json) {
        try {
            Set<Seat> seats = new HashSet<>();

            JsonNode jsonNode = this.objectMapper.readTree(json);
            Iterator<JsonNode> seatsNode = jsonNode.get("seats").elements();
            while (seatsNode.hasNext()) {
                JsonNode seatNode = seatsNode.next();
                seats.add(Seat.reserved(
                        seatNode.get("seat_number").asInt(),
                        seatNode.get("coach").asText(),
                        BookingReference.valueOf(seatNode.get("booking_reference").asText())));
            }

            return TrainSnapshot.create(trainId, seats);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Reservation reservation) {
        System.out.println("Register a reservation: " + reservation);
    }
}
