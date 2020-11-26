package com.excilys.katatrain.infra.adapters;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.ports.BookingReferenceProvider;
import com.excilys.katatrain.external.services.BookingReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingReferenceAdapter implements BookingReferenceProvider {
    private final BookingReferenceService bookingReferenceService;

    @Autowired
    public BookingReferenceAdapter(BookingReferenceService bookingReferenceService) {
        this.bookingReferenceService = bookingReferenceService;
    }

    @Override
    public BookingReference get() {
        return BookingReference.from(this.bookingReferenceService.retrieveReference());
    }
}
