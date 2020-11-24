package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.Seat;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class TicketServiceTest {
    private TicketService ticketService = new TicketService();

    @Test
    public void should_reserve_seats_when_unreserved_seats_are_available() {
        String trainId = "TGV2611";
        String bookingReference = "XCLSDDD";
        Set<Seat> seats = Set.of(Seat.of(1, "A"), Seat.of(2, "A"), Seat.of(3, "A"));
        Reservation expectedReservation = Reservation.with(trainId, BookingReference.of(bookingReference), seats);

        Reservation reservation = ticketService.reserve(2, trainId);

        assertThat(reservation).isNotNull();
        assertThat(reservation).isEqualTo(expectedReservation);
    }

    @Test
    public void should_not_reserve_when_train_is_full() {
        fail("Not implemented yet.");
    }

    @Test
    public void should_not_reserve_when_train_exceeds_the_limit() {
        fail("Not implemented yet.");
    }

    @Test
    public void should_retrieve_booking_reference_to_reserve() {
        fail("Not implemented yet.");
    }

    @Test
    public void should_mark_seats_as_reserved_once_reserved() {
        fail("Not implemented yet.");
    }
}
