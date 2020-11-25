package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.ReservationSuggestion;
import com.excilys.katatrain.domain.core.TrainSnapshot;
import com.excilys.katatrain.domain.core.exceptions.ReservationException;
import com.excilys.katatrain.domain.ports.TrainDataProvider;

import java.util.Objects;

public class TicketService {
    private final TrainDataProvider trainDataProvider;

    public TicketService(TrainDataProvider trainDataProvider) {
        Objects.requireNonNull(trainDataProvider);
        this.trainDataProvider = trainDataProvider;
    }

    public Reservation reserve(int numberOfSeats, String trainId) throws ReservationException {
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Reserve at least 1 seat");
        }

        TrainSnapshot trainSnapshot = this.trainDataProvider.getTrain(trainId);

        ReservationSuggestion suggestion = trainSnapshot.search(numberOfSeats);
        if (suggestion.isFullfilled()) {
            Reservation reservation = suggestion.confirm(BookingReference.none());
            this.trainDataProvider.register(reservation);
            return reservation;
        }

        throw new ReservationException("Not satisfied reservation: " + suggestion);
    }
}
