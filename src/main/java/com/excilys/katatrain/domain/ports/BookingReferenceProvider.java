package com.excilys.katatrain.domain.ports;

import com.excilys.katatrain.domain.core.BookingReference;

import java.util.function.Supplier;

public interface BookingReferenceProvider extends Supplier<BookingReference> {

}
