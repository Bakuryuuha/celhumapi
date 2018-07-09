package com.api.celhum.service;

import com.api.celhum.model.Booking;
import com.api.celhum.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service(value = "bookingRepositoryCustom")
public class BookingRepositoryImpl implements BookingRepositoryCustom {

    BookingRepository bookingRepository;

    @Override
    public Booking save(Booking book) {
        return bookingRepository.save(book);
    }
}
