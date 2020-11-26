package com.excilys.katatrain.config;

import com.excilys.katatrain.domain.core.BookingReference;
import com.excilys.katatrain.domain.core.Reservation;
import com.excilys.katatrain.domain.core.Seat;
import com.excilys.katatrain.domain.core.TrainSnapshot;
import com.excilys.katatrain.domain.ports.BookingReferenceProvider;
import com.excilys.katatrain.domain.ports.TrainDataProvider;
import com.excilys.katatrain.domain.services.TicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class ReservationConfig {
    @Bean
    public TicketService ticketService(TrainDataProvider trainDataProvider, BookingReferenceProvider bookingReferenceProvider) {
        return new TicketService(trainDataProvider, bookingReferenceProvider);
    }

    @Bean
    public TrainDataProvider trainDataProvider() {
        return new TrainDataProvider() {
            @Override
            public TrainSnapshot getTrain(String trainId) {
                Set<Seat> seats = IntStream.rangeClosed(1, 10).mapToObj(i -> Seat.unreserved(i, "A")).collect(Collectors.toSet());
                return TrainSnapshot.create("TGV2611", seats);
            }

            @Override
            public void register(Reservation reservation) {
                System.out.println("Store new reservation: " + reservation);
            }
        };
    }

    @Bean
    public BookingReferenceProvider bookingReferenceProvider() {
        return () -> BookingReference.from("XCLSDDD");
    }
}
