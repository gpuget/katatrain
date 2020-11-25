package com.excilys.katatrain.domain.core;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationSuggestionTest extends ValueObjectTest<ReservationSuggestion> {
    @Override
    protected ReservationSuggestion get() {
        return ReservationSuggestion.create(2, "test", Set.of(Seat.unreserved(1, "A")));
    }

    @Test
    public void isFullfilled() {
        assertThat(get().isFullfilled()).isFalse();
    }

    @Test
    public void confirm() {
        Reservation expectedReservation = Reservation.create("test", BookingReference.none(), Set.of(Seat.unreserved(1, "A")));
        assertThat(get().confirm(BookingReference.none())).isEqualTo(expectedReservation);
    }
}