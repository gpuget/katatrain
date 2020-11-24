package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.Seat;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicketService {
    public Reservation reserve(int numberOfSeats, String trainId) {
        String bookingReference = "XXX"; // TODO
        Set<Seat> seats = IntStream.range(0, numberOfSeats).mapToObj(i -> new Seat()).collect(Collectors.toSet()); // TODO
        return Reservation.of(trainId, bookingReference, seats);
    }
}
