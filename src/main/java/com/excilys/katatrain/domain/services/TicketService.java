package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.annotations.DomainService;
import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.ReservationSuggestion;
import com.excilys.katatrain.domain.core.TrainSnapshot;
import com.excilys.katatrain.domain.core.exceptions.ReservationException;
import com.excilys.katatrain.domain.ports.BookingReferenceProvider;
import com.excilys.katatrain.domain.ports.TrainDataProvider;

import static java.util.Objects.requireNonNull;

@DomainService
public class TicketService {
    private final TrainDataProvider trainDataProvider;
    private final BookingReferenceProvider bookingReferenceProvider;

    public TicketService(TrainDataProvider trainDataProvider, BookingReferenceProvider bookingReferenceProvider) {
        this.trainDataProvider = requireNonNull(trainDataProvider);
        this.bookingReferenceProvider = requireNonNull(bookingReferenceProvider);

    }

    public Reservation reserve(int numberOfSeats, String trainId) throws ReservationException {
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Reserve at least 1 seat");
        }

        TrainSnapshot trainSnapshot = this.trainDataProvider.getTrainSnapshot(trainId);

        ReservationSuggestion suggestion = trainSnapshot.search(numberOfSeats);
        if (suggestion.isFullfilled()) {
            BookingReference bookingReference = this.bookingReferenceProvider.get();
            Reservation reservation = suggestion.confirm(bookingReference);
            this.trainDataProvider.register(reservation);
            return reservation;
        }

        throw new ReservationException("Not satisfied reservation: " + suggestion);
    }
}
