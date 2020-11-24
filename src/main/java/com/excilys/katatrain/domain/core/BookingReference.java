package com.excilys.katatrain.domain.core;

import java.util.Objects;

public class BookingReference {
    private static final BookingReference NONE = new BookingReference("");

    private final String reference;

    private BookingReference(String reference) {
        this.reference = reference;
    }

    public static BookingReference of(String reference) {
        Objects.requireNonNull(reference);
        return new BookingReference(reference);
    }

    public static BookingReference none() {
        return NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingReference that = (BookingReference) o;
        return Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

    @Override
    public String toString() {
        return reference;
    }
}
