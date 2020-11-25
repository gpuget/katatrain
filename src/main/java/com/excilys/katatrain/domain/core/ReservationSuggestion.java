package com.excilys.katatrain.domain.core;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class ReservationSuggestion {
    private final int numberOfSeatsToReserve;
    private final String trainId;
    private final Set<Seat> seats;

    private ReservationSuggestion(int numberOfSeatsToReserve, String trainId, Set<Seat> seats) {
        this.numberOfSeatsToReserve = numberOfSeatsToReserve;
        this.trainId = trainId;
        this.seats = seats;
    }

    public static ReservationSuggestion create(int numberOfSeatsToReserve, String trainId, Set<Seat> seats) {
        return new ReservationSuggestion(numberOfSeatsToReserve, trainId, Collections.unmodifiableSet(seats));
    }

    public static ReservationSuggestion none(int numberOfSeatsToReserve, String trainId) {
        return create(numberOfSeatsToReserve, trainId, Collections.emptySet());
    }

    public Reservation confirm(BookingReference bookingReference) {
        return Reservation.create(this.trainId, bookingReference, seats);
    }

    public boolean isFullfilled() {
        return this.seats.size() == this.numberOfSeatsToReserve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationSuggestion that = (ReservationSuggestion) o;
        return numberOfSeatsToReserve == that.numberOfSeatsToReserve &&
                Objects.equals(trainId, that.trainId) &&
                Objects.equals(seats, that.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSeatsToReserve, trainId, seats);
    }

    @Override
    public String toString() {
        return "Train (" + this.trainId + "): " +
                this.seats.size() + '/' + this.numberOfSeatsToReserve + " " +
                this.seats;
    }
}
