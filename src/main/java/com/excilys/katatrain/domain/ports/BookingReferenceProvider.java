package com.excilys.katatrain.domain.ports;

import com.excilys.katatrain.domain.annotations.Port;
import com.excilys.katatrain.domain.core.BookingReference;

import java.util.function.Supplier;

@Port
public interface BookingReferenceProvider extends Supplier<BookingReference> {

}
