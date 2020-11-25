package com.excilys.katatrain.domain.core;

import java.util.Collections;
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

    public Reservation confirm(BookingReference bookingReference) {
        return Reservation.create(this.trainId, bookingReference, seats);
    }

    public boolean isFullfilled() {
        return this.seats.size() == this.numberOfSeatsToReserve;
    }

    @Override
    public String toString() {
        return "Train [" + this.trainId + "]: " + this.seats.size() + '/' + this.numberOfSeatsToReserve;
    }
}
