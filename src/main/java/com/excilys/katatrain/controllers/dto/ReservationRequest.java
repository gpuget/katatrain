package com.excilys.katatrain.controllers.dto;

public class ReservationRequest {
    private String trainId;
    private int seats;

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
