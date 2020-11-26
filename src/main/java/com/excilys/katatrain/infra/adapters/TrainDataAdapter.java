package com.excilys.katatrain.infra.adapters;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.Seat;
import com.excilys.katatrain.domain.core.TrainSnapshot;
import com.excilys.katatrain.domain.ports.TrainDataProvider;
import com.excilys.katatrain.external.services.TrainDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class TrainDataAdapter implements TrainDataProvider {
    private final TrainDataService trainDataService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TrainDataAdapter(TrainDataService trainDataService, ObjectMapper objectMapper) {
        this.trainDataService = trainDataService;
        this.objectMapper = objectMapper;
    }

    @Override
    public TrainSnapshot getTrain(String trainId) {
        String json = this.trainDataService.getTrain(trainId);
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
                        BookingReference.from(seatNode.get("booking_reference").asText())));
            }

            return TrainSnapshot.create(trainId, seats);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void register(Reservation reservation) {
        System.out.println("Register a reservation: " + reservation);
    }
}
