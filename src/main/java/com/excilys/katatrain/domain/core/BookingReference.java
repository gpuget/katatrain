package com.excilys.katatrain.domain.core;

import com.excilys.katatrain.domain.annotations.FactoryMethod;
import com.excilys.katatrain.domain.annotations.NullObject;
import com.excilys.katatrain.domain.annotations.ValueObject;

import java.util.Objects;

@ValueObject
public class BookingReference {
    @NullObject
    private static final BookingReference NONE = new BookingReference("");

    private final String reference;

    private BookingReference(String reference) {
        this.reference = reference;
    }

    @FactoryMethod
    public static BookingReference valueOf(String reference) {
        if (reference == null || reference.isBlank()) {
            return NONE;
        }
        return new BookingReference(reference);
    }

    @FactoryMethod
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
