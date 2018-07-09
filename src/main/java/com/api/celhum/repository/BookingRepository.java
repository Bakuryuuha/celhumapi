package com.api.celhum.repository;

import com.api.celhum.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking,String> {

    Long countBookingByDepartdate(Date departdate);

    Long countBookingByDepartdateIsBefore(Date departdate);

    List<Booking> findBookingByBookstatusEquals(String status);

    Long countBookingsByBookstatusEquals(String status);

    List<Booking> findBookingByOwner(String id);

    Booking findBookingById(String id);
}
