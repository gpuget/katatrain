package com.excilys.katatrain.controllers.dto;

import com.excilys.katatrain.model.Reservation;
import com.excilys.katatrain.model.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationDto {
    private String trainId;
    private String bookingReference;
    private List<String> seats;

    public static ReservationDto map(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.trainId = reservation.getTrainId();
        dto.bookingReference = reservation.getBookingReference().toString();
        dto.seats = reservation.getSeats().stream().map(Seat::toString).collect(Collectors.toList());
        return dto;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }
}
