package com.excilys.katatrain.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeatTest extends ValueObjectTest<Seat> {
    @Override
    protected Seat get() {
        return Seat.create(1, "A", BookingReference.none());
    }

    @Test
    public void isReserved() {
        assertThat(get().isReserved())
                .isFalse()
                .isNotEqualTo(get().isNotReserved());
    }
}
