package com.excilys.katatrain.domain.core;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainSnapshot {
    private final Set<Seat> seats;

    private TrainSnapshot(Set<Seat> seats) {
        this.seats = seats;
    }

    public static TrainSnapshot from(Set<Seat> seats) {
        return new TrainSnapshot(Collections.unmodifiableSet(seats));
    }

    public Set<Seat> getUnreservedSeats(int numberOfSeats) {
        return this.seats.stream().limit(numberOfSeats).collect(Collectors.toSet());
    }
}
