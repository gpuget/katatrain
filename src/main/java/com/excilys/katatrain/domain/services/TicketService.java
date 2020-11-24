package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;

import java.util.Collections;

public class TicketService {
    public Reservation reserve(int numberOfSeats, String trainId) {
        return Reservation.with(trainId, BookingReference.none(), Collections.emptySet());
    }
}
