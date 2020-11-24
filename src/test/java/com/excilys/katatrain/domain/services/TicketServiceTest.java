package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.core.Reservation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class TicketServiceTest {
    private TicketService ticketService = new TicketService();

    @Test
    public void should_reserve_seats_when_unreserved_seats_are_available() {
        Reservation reservation = ticketService.reserve(3, "TGV2611");
        assertThat(reservation).isNotNull();
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
