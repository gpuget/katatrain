package com.excilys.katatrain.domain.services;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.Seat;
import com.excilys.katatrain.domain.core.TrainSnapshot;
import com.excilys.katatrain.domain.ports.TrainDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private TrainDataProvider trainDataProvider;
    @InjectMocks
    private TicketService ticketService;

    private static TrainSnapshot emptyTrainSnapshot(int numberOfSeats) {
        return trainSnapshot(numberOfSeats, 0);
    }

    private static TrainSnapshot trainSnapshot(int numberOfSeats, int numberOfReserved) {
        Set<Seat> reservedSeats = IntStream.rangeClosed(1, numberOfReserved)
                .mapToObj(i -> Seat.reserved(i, "A", BookingReference.of("XCLSDDD")))
                .collect(Collectors.toSet());
        Set<Seat> unreservedSeats = IntStream.rangeClosed(numberOfReserved + 1, numberOfSeats)
                .mapToObj(i -> Seat.unreserved(i, "A"))
                .collect(Collectors.toSet());
        return TrainSnapshot.from(Stream.of(reservedSeats, unreservedSeats)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet()));
    }

    @Test
    public void should_reserve_seats_when_unreserved_seats_are_available() {
        // GIVEN
        String trainId = "TGV2611";
        int numberOfSeats = 2;
        TrainSnapshot trainSnapshot = trainSnapshot(10, 3);
        when(this.trainDataProvider.getTrain(trainId)).thenReturn(trainSnapshot);

        // WHEN
        Reservation reservation = this.ticketService.reserve(numberOfSeats, trainId);

        // THEN
        assertThat(reservation).isNotNull();
        assertThat(reservation.numberOfSeats()).isEqualTo(numberOfSeats);
    }

    @Test
    public void should_not_reserve_when_train_is_full() {
        fail("Not implemented yet.");
    }

    @Test
    public void should_not_reserve_when_train_exceeds_the_limit() {
        fail("Not implemented yet.");
    }

    @Test
    public void should_retrieve_booking_reference_to_reserve() {
        fail("Not implemented yet.");
    }

    @Test
    public void should_mark_seats_as_reserved_once_reserved() {
        fail("Not implemented yet.");
    }
}
