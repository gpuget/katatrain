package com.excilys.katatrain.domain.core;

import java.util.Objects;

public class Seat {
    private final int number;
    private final String coach;
    private final BookingReference bookingReference;

    private Seat(int number, String coach, BookingReference bookingReference) {
        this.number = number;
        this.coach = coach;
        this.bookingReference = bookingReference;
    }

    public static Seat reserved(int number, String coach, BookingReference bookingReference) {
        return create(number, coach, bookingReference);
    }

    public static Seat unreserved(int number, String coach) {
        return create(number, coach, BookingReference.none());
    }

    private static Seat create(int number, String coach, BookingReference bookingReference) {
        checkNumber(number);
        Objects.requireNonNull(coach);
        Objects.requireNonNull(bookingReference);
        return new Seat(number, coach, bookingReference);
    }

    private static int checkNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("negative number of seat");
        }

        return number;
    }

    public boolean isNotReserved() {
        return BookingReference.none().equals(this.bookingReference);
    }

    public boolean isReserved() {
        return !isNotReserved();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return number == seat.number &&
                Objects.equals(coach, seat.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, coach);
    }

    @Override
    public String toString() {
        return number + coach;
    }
}
