package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;

import java.util.Collections;

public class TicketService {
    public Reservation reserve(ReservationRequest request) {
        return Reservation.with(request.trainId, BookingReference.none(), Collections.emptySet());
    }

    public static class ReservationRequest {
        private final int numberOfSeats;
        private final String trainId;

        private ReservationRequest(int numberOfSeats, String trainId) {
            this.numberOfSeats = numberOfSeats;
            this.trainId = trainId;
        }

        public static ReservationRequest of(int numberOfSeats, String trainId) {
            if (numberOfSeats <= 0) {
                throw new IllegalArgumentException("must reserve at least 1 seat");
            }

            if (trainId == null || trainId.isBlank()) {
                throw new IllegalArgumentException("invalid train id");
            }

            return new ReservationRequest(numberOfSeats, trainId);
        }
    }
}
