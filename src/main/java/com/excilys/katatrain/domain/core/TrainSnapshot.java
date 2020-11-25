package com.excilys.katatrain.domain.core;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainSnapshot {
    private final String trainId;
    private final Set<Seat> seats;

    private TrainSnapshot(String trainId, Set<Seat> seats) {
        this.trainId = trainId;
        this.seats = seats;
    }

    public static TrainSnapshot create(String trainId, Set<Seat> seats) {
        return new TrainSnapshot(trainId, Collections.unmodifiableSet(seats));
    }

    public ReservationSuggestion search(int numberOfSeats) {
        Set<Seat> limitedUnreservedSeats = unreservedSeats(numberOfSeats);
        return ReservationSuggestion.create(numberOfSeats, this.trainId, limitedUnreservedSeats);
    }

    private Set<Seat> unreservedSeats(int number) {
        return this.seats.stream()
                .filter(Seat::isNotReserved)
                .limit(number)
                .collect(Collectors.toSet());
    }
}
