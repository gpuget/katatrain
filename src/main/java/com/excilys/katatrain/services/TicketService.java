package com.excilys.katatrain.services;

import com.excilys.katatrain.model.*;
import com.excilys.katatrain.model.exceptions.ReservationException;
import com.excilys.katatrain.services.externals.BookingReferenceService;
import org.springframework.stereotype.Service;

import java.util.Set;

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

        Set<Seat> unreservedSeats = this.trainDataService.getUnreservedSeats(numberOfSeats, trainId);
        if (isFullfilled(unreservedSeats, numberOfSeats)) {
            BookingReference bookingReference = BookingReference.valueOf(this.bookingReferenceService.retrieveReference());
            Reservation reservation = confirm(trainId, bookingReference, unreservedSeats);
            this.trainDataService.save(reservation);
            return reservation;
        }

        throw new ReservationException("Not satisfied reservation:");
    }

    private boolean isFullfilled(Set<Seat> seats, int number) {
        return seats.size() == number;
    }

    private Reservation confirm(String trainId, BookingReference bookingReference, Set<Seat> seats) {
        return Reservation.create(trainId, bookingReference, seats);
    }
}
