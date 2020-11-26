package com.excilys.katatrain.infra.controllers;

import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.exceptions.ReservationException;
import com.excilys.katatrain.domain.services.TicketService;
import com.excilys.katatrain.infra.controllers.dto.ReservationDto;
import com.excilys.katatrain.infra.controllers.dto.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> performReservation(@RequestBody ReservationRequest reservationRequest) {
        try {
            Reservation reservation = this.ticketService.reserve(reservationRequest.getSeats(), reservationRequest.getTrainId());
            return ResponseEntity.ok(ReservationDto.from(reservation));
        } catch (ReservationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
