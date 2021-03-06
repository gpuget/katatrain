package com.excilys.katatrain.infra.controllers.dto;

import com.excilys.katatrain.infra.annotations.DTO;

@DTO
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
