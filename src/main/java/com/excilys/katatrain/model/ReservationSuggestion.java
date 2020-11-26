package com.excilys.katatrain.model;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class ReservationSuggestion {
    private final int expectedNumber;
    private final String trainId;
    private final Set<Seat> seats;

    private ReservationSuggestion(int expectedNumber, String trainId, Set<Seat> seats) {
        this.expectedNumber = expectedNumber;
        this.trainId = trainId;
        this.seats = seats;
    }

    public static ReservationSuggestion create(int expectedNumber, String trainId, Set<Seat> seats) {
        return new ReservationSuggestion(expectedNumber, trainId, Collections.unmodifiableSet(seats));
    }

    public static ReservationSuggestion none(int expectedNumber, String trainId) {
        return new ReservationSuggestion(expectedNumber, trainId, Collections.emptySet());
    }

    public Reservation confirm(BookingReference bookingReference) {
        return Reservation.create(this.trainId, bookingReference, seats);
    }

    public boolean isFullfilled() {
        return this.seats.size() == this.expectedNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationSuggestion that = (ReservationSuggestion) o;
        return expectedNumber == that.expectedNumber &&
                Objects.equals(trainId, that.trainId) &&
                Objects.equals(seats, that.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expectedNumber, trainId, seats);
    }

    @Override
    public String toString() {
        return "Suggestion [" + this.trainId + "]: " + this.seats + " (" + this.seats.size() + '/' + ")";
    }
}
