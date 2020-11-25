package com.excilys.katatrain.domain.core;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainSnapshot {
    private static final float RESERVABLE_SEAT_PERCENT = 0.7F;

    private final String trainId;
    private final Set<Seat> reservedSeats;
    private final Set<Seat> unreservedSeats;
    private final int capacity;
    private final int maxReservableSeats;

    private TrainSnapshot(String trainId, Set<Seat> seats) {
        Map<Boolean, List<Seat>> tmp = seats.stream().collect(Collectors.partitioningBy(Seat::isReserved));

        this.trainId = trainId;
        this.reservedSeats = Set.copyOf(tmp.get(Boolean.TRUE));
        this.unreservedSeats = Set.copyOf(tmp.get(Boolean.FALSE));
        this.capacity = seats.size();
        this.maxReservableSeats = Math.round(seats.size() * RESERVABLE_SEAT_PERCENT);
    }

    public static TrainSnapshot create(String trainId, Set<Seat> seats) {
        return new TrainSnapshot(trainId, seats);
    }

    public ReservationSuggestion search(int numberOfSeats) {
        if (!allowedToReserve(numberOfSeats)) {
            return ReservationSuggestion.none(numberOfSeats, this.trainId);
        }

        Set<Seat> selectedSeats = select(numberOfSeats);
        return ReservationSuggestion.create(numberOfSeats, this.trainId, selectedSeats);
    }

    private boolean allowedToReserve(int numberOfSeatsToReserve) {
        return (this.reservedSeats.size() + numberOfSeatsToReserve) <= this.maxReservableSeats;
    }

    private Set<Seat> select(int number) {
        return this.unreservedSeats.stream().limit(number).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainSnapshot that = (TrainSnapshot) o;
        return capacity == that.capacity &&
                maxReservableSeats == that.maxReservableSeats &&
                Objects.equals(trainId, that.trainId) &&
                Objects.equals(reservedSeats, that.reservedSeats) &&
                Objects.equals(unreservedSeats, that.unreservedSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, reservedSeats, unreservedSeats, capacity, maxReservableSeats);
    }
}
