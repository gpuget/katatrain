package com.excilys.katatrain.external.services;

import java.util.Random;

public class BookingReferenceService {
    public String retrieveReference() {
        return generateRandomString();
    }

    private String generateRandomString() {
        return new Random().ints((int) 'A', ((int) 'Z') + 1)
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
