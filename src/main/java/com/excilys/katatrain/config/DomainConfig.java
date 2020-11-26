package com.excilys.katatrain.config;

import com.excilys.katatrain.domain.ports.BookingReferenceProvider;
import com.excilys.katatrain.domain.ports.TrainDataProvider;
import com.excilys.katatrain.domain.services.TicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
    @Bean
    public TicketService ticketService(TrainDataProvider trainDataProvider, BookingReferenceProvider bookingReferenceProvider) {
        return new TicketService(trainDataProvider, bookingReferenceProvider);
    }
}
