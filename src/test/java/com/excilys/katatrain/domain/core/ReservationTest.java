package com.excilys.katatrain.domain.core;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationTest extends ValueObjectTest<Reservation> {
    @Override
    protected Reservation get() {
        return Reservation.create("test", BookingReference.none(), Set.of(Seat.unreserved(1, "A")));
    }

    @Test
    public void numberOfSeats() {
        assertThat(get().numberOfSeats()).isEqualTo(1);
    }
}
