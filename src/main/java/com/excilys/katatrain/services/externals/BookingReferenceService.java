package com.excilys.katatrain.services.externals;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
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
