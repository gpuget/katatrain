package com.excilys.katatrain.domain.core;

import java.util.Objects;

public class Seat {
    private final int number;
    private final String coach;

    private Seat(int number, String coach) {
        this.number = number;
        this.coach = coach;
    }

    public static Seat of(int number, String coach) {
        checkNumber(number);
        Objects.requireNonNull(coach);
        return new Seat(number, coach);
    }

    private static int checkNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("negative number of seat");
        }

        return number;
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
