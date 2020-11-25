package com.excilys.katatrain.domain.core;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainSnapshotTest extends ValueObjectTest<TrainSnapshot> {
    @Override
    protected TrainSnapshot get() {
        return TrainSnapshot.create("test", Set.of(Seat.unreserved(1, "A")));
    }

    @Test
    public void search_only_unreserved_seats() {
        int numberOfSeats = 2;
        ReservationSuggestion expectedSuggestion = ReservationSuggestion.create(numberOfSeats, "test", Set.of(
                Seat.unreserved(1, "A")));

        TrainSnapshot trainSnapshot = TrainSnapshot.create("test", Set.of(
                Seat.unreserved(1, "A"),
                Seat.reserved(2, "A", BookingReference.from("test"))));

        assertThat(trainSnapshot.search(numberOfSeats)).isEqualTo(expectedSuggestion);
    }

    @Test
    public void search_only_limited_number_of_seats() {
        int numberOfSeats = 1;
        ReservationSuggestion expectedSuggestion = ReservationSuggestion.create(numberOfSeats, "test", Set.of(
                Seat.unreserved(2, "A")));

        TrainSnapshot trainSnapshot = TrainSnapshot.create("test", Set.of(
                Seat.unreserved(1, "A"),
                Seat.unreserved(2, "A")));

        assertThat(trainSnapshot.search(numberOfSeats)).isEqualTo(expectedSuggestion);
    }
}