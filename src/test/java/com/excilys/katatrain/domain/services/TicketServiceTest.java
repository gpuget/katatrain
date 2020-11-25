package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.Seat;
import com.excilys.katatrain.domain.core.TrainSnapshot;
import com.excilys.katatrain.domain.core.exceptions.ReservationException;
import com.excilys.katatrain.domain.ports.TrainDataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private TrainDataProvider trainDataProvider;
    @InjectMocks
    private TicketService ticketService;

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(this.trainDataProvider);
    }

    private static TrainSnapshot trainSnapshot(String trainId, int numberOfSeats, int numberOfReserved) {
        Set<Seat> reservedSeats = IntStream.rangeClosed(1, numberOfReserved)
                .mapToObj(i -> Seat.reserved(i, "A", BookingReference.from("XCLSDDD")))
                .collect(Collectors.toSet());
        Set<Seat> unreservedSeats = (numberOfReserved < numberOfSeats)
                ? IntStream.rangeClosed(numberOfReserved + 1, numberOfSeats)
                .mapToObj(i -> Seat.unreserved(i, "A"))
                .collect(Collectors.toSet())
                : Collections.emptySet();
        return TrainSnapshot.create(trainId, Stream.of(reservedSeats, unreservedSeats)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet()));
    }

    @Test
    public void should_reserve_seats_when_unreserved_seats_are_available() throws ReservationException {
        // GIVEN
        String trainId = "TGV2611";
        int numberOfSeats = 2;
        TrainSnapshot trainSnapshot = trainSnapshot(trainId, 10, 3);
        when(this.trainDataProvider.getTrain(trainId)).thenReturn(trainSnapshot);

        // WHEN
        Reservation reservation = this.ticketService.reserve(numberOfSeats, trainId);

        // THEN
        assertThat(reservation).isNotNull();
        assertThat(reservation.numberOfSeats()).isEqualTo(numberOfSeats);
        verify(this.trainDataProvider).register(reservation);
    }

    @Test
    public void should_not_reserve_when_train_is_full() {
        // GIVEN
        String trainId = "TGV2611";
        int numberOfSeats = 2;
        TrainSnapshot trainSnapshot = trainSnapshot(trainId, 10, 10);
        when(this.trainDataProvider.getTrain(trainId)).thenReturn(trainSnapshot);

        // WHEN THEN
        assertThatThrownBy(() -> this.ticketService.reserve(numberOfSeats, trainId))
                .isInstanceOf(ReservationException.class)
                .hasMessageContaining("Not satisfied reservation");
    }

    @Test
    public void should_not_reserve_when_not_enough_available_seats() {
        // GIVEN
        String trainId = "TGV2611";
        int numberOfSeats = 2;
        TrainSnapshot trainSnapshot = trainSnapshot(trainId, 1, 0);
        when(this.trainDataProvider.getTrain(trainId)).thenReturn(trainSnapshot);

        // WHEN THEN
        assertThatThrownBy(() -> this.ticketService.reserve(numberOfSeats, trainId))
                .isInstanceOf(ReservationException.class)
                .hasMessageContaining("Not satisfied reservation");
    }

    @Test
    public void should_not_reserve_when_not_enough_unreserved_seats() {
        // GIVEN
        String trainId = "TGV2611";
        int numberOfSeats = 2;
        TrainSnapshot trainSnapshot = trainSnapshot(trainId, 10, 9);
        when(this.trainDataProvider.getTrain(trainId)).thenReturn(trainSnapshot);

        // WHEN THEN
        assertThatThrownBy(() -> this.ticketService.reserve(numberOfSeats, trainId))
                .isInstanceOf(ReservationException.class)
                .hasMessageContaining("Not satisfied reservation");
    }

    @Test
    public void should_throw_exception_if_try_to_reserve_zero_seat() {
        // GIVEN
        String trainId = "TGV2611";
        int numberOfSeats = 0;

        // WHEN THEN
        assertThrows(IllegalArgumentException.class, () -> this.ticketService.reserve(numberOfSeats, trainId));
    }

    @Test
    @Disabled
    public void should_not_reserve_when_train_exceeds_the_limit() {
        fail("Not implemented yet.");
    }

    @Test
    @Disabled
    public void should_retrieve_booking_reference_to_reserve() {
        fail("Not implemented yet.");
    }

    @Test
    public void should_register_reservation_after_reserve() throws ReservationException {
        // GIVEN
        String trainId = "TGV2611";
        int numberOfSeats = 2;
        TrainSnapshot trainSnapshot = trainSnapshot(trainId, 10, 3);
        when(this.trainDataProvider.getTrain(trainId)).thenReturn(trainSnapshot);

        // WHEN
        Reservation reservation = this.ticketService.reserve(numberOfSeats, trainId);

        // THEN
        verify(this.trainDataProvider).register(reservation);
    }
}
