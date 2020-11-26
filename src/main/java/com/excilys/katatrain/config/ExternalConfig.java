package com.excilys.katatrain.config;

import com.excilys.katatrain.external.services.BookingReferenceService;
import com.excilys.katatrain.external.services.TrainDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalConfig {
    @Bean
    public TrainDataService trainDataService() {
        return new TrainDataService();
    }

    @Bean
    public BookingReferenceService bookingReferenceService() {
        return new BookingReferenceService();
    }
}
