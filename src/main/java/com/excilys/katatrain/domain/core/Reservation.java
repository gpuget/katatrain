package com.excilys.katatrain.domain.core;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class Reservation {
    private final String trainId;
    private final BookingReference bookingReference;
    private final Set<Seat> seats;

    private Reservation(String trainId, BookingReference bookingReference, Set<Seat> seats) {
        this.trainId = trainId;
        this.bookingReference = bookingReference;
        this.seats = seats;
    }

    public static Reservation with(String trainId, BookingReference bookingReference, Set<Seat> seats) {
        return new Reservation(trainId, bookingReference, Collections.unmodifiableSet(seats));
    }

    public int numberOfSeats() {
        return this.seats.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(trainId, that.trainId) &&
                Objects.equals(bookingReference, that.bookingReference) &&
                Objects.equals(seats, that.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, bookingReference, seats);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "trainId='" + trainId + '\'' +
                ", bookingReference=" + bookingReference +
                ", seats=" + seats +
                '}';
    }
}
