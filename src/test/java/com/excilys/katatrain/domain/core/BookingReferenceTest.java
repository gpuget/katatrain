package com.excilys.katatrain.domain.core;

public class BookingReferenceTest extends ValueObjectTest<BookingReference> {
    @Override
    protected BookingReference get() {
        return BookingReference.from("test");
    }
}
