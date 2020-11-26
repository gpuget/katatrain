package com.excilys.katatrain.domain.core;

import java.util.Set;

public class TrainSnapshotTest extends ValueObjectTest<TrainSnapshot> {
    @Override
    protected TrainSnapshot get() {
        return TrainSnapshot.create("test", Set.of(
                Seat.reserved(1, "A", BookingReference.valueOf("test")),
                Seat.reserved(2, "A", BookingReference.valueOf("test")),
                Seat.unreserved(3, "A"),
                Seat.unreserved(4, "A"),
                Seat.unreserved(5, "A"),
                Seat.unreserved(6, "A"),
                Seat.unreserved(7, "A"),
                Seat.unreserved(8, "A"),
                Seat.unreserved(9, "A"),
                Seat.unreserved(10, "A")));
    }
}