package com.excilys.katatrain.services.externals;

import org.springframework.stereotype.Service;

@Service
public class TrainDataProvider {
    public String getTrain(String trainId) {
        return "{ \"seats\": {" +
                "\"1A\": { \"booking_reference\":\"\", \"seat_number\": 1, \"coach\": \"A\" }, " +
                "\"2A\": { \"booking_reference\":\"\", \"seat_number\": 2, \"coach\": \"A\" }, " +
                "\"3A\": { \"booking_reference\":\"\", \"seat_number\": 3, \"coach\": \"A\" }, " +
                "\"4A\": { \"booking_reference\":\"\", \"seat_number\": 4, \"coach\": \"A\" }, " +
                "\"5A\": { \"booking_reference\":\"\", \"seat_number\": 5, \"coach\": \"A\" }, " +
                "\"6A\": { \"booking_reference\":\"\", \"seat_number\": 6, \"coach\": \"A\" }, " +
                "\"7A\": { \"booking_reference\":\"\", \"seat_number\": 7, \"coach\": \"A\" }, " +
                "\"8A\": { \"booking_reference\":\"\", \"seat_number\": 8, \"coach\": \"A\" }, " +
                "\"9A\": { \"booking_reference\":\"\", \"seat_number\": 9, \"coach\": \"A\" }, " +
                "\"10A\": { \"booking_reference\":\"\", \"seat_number\": 10, \"coach\": \"A\" } " +
                "}}";
    }
}
