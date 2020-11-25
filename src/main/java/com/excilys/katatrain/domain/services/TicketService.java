package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.Seat;
import com.excilys.katatrain.domain.core.TrainSnapshot;
import com.excilys.katatrain.domain.ports.TrainDataProvider;

import java.util.Objects;
import java.util.Set;

public class TicketService {
    private final TrainDataProvider trainDataProvider;

    public TicketService(TrainDataProvider trainDataProvider) {
        Objects.requireNonNull(trainDataProvider);
        this.trainDataProvider = trainDataProvider;
    }

    public Reservation reserve(int numberOfSeats, String trainId) {
        TrainSnapshot trainSnapshot = this.trainDataProvider.getTrain(trainId);
        Set<Seat> unreservedSeats = trainSnapshot.getUnreservedSeats(numberOfSeats);
        return Reservation.with(trainId, BookingReference.none(), unreservedSeats);
    }
}
