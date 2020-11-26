package com.excilys.katatrain.services;

import com.excilys.katatrain.model.BookingReference;
import com.excilys.katatrain.model.Reservation;
import com.excilys.katatrain.model.ReservationSuggestion;
import com.excilys.katatrain.model.TrainSnapshot;
import com.excilys.katatrain.model.exceptions.ReservationException;
import com.excilys.katatrain.services.externals.BookingReferenceService;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

@Service
public class TicketService {
    private final TrainDataService trainDataService;
    private final BookingReferenceService bookingReferenceService;

    public TicketService(TrainDataService trainDataService, BookingReferenceService bookingReferenceService) {
        this.trainDataService = requireNonNull(trainDataService);
        this.bookingReferenceService = requireNonNull(bookingReferenceService);

    }

    public Reservation reserve(int numberOfSeats, String trainId) throws ReservationException {
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Reserve at least 1 seat");
        }

        TrainSnapshot trainSnapshot = this.trainDataService.getTrainSnapshot(trainId);

        ReservationSuggestion suggestion = trainSnapshot.search(numberOfSeats);
        if (suggestion.isFullfilled()) {
            BookingReference bookingReference = BookingReference.valueOf(this.bookingReferenceService.retrieveReference());
            Reservation reservation = suggestion.confirm(bookingReference);
            this.trainDataService.save(reservation);
            return reservation;
        }

        throw new ReservationException("Not satisfied reservation: " + suggestion);
    }
}
