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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        Set<Seat> unreservedSeats = IntStream.rangeClosed(1, numberOfSeats).mapToObj(i -> Seat.of(i, "A")).collect(Collectors.toSet());
        return TrainSnapshot.from(unreservedSeats);
    }

    @Test
    public void should_reserve_seats_when_unreserved_seats_are_available() {
        String trainId = "TGV2611";
        BookingReference bookingReference = BookingReference.none();
        Set<Seat> seats = Set.of(Seat.of(1, "A"), Seat.of(2, "A"), Seat.of(3, "A"));
        Reservation expectedReservation = Reservation.with(trainId, bookingReference, seats);
        when(this.trainDataProvider.getTrain(trainId)).thenReturn(emptyTrainSnapshot(3));

        Reservation reservation = this.ticketService.reserve(3, trainId);

        assertThat(reservation).isNotNull();
        assertThat(reservation).isEqualTo(expectedReservation);
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
