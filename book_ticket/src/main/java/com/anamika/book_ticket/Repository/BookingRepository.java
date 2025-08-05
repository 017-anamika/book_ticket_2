package com.anamika.book_ticket.Repository;

import com.anamika.book_ticket.Entity.Booking;
import com.anamika.book_ticket.Entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserID(Long userId);
    List<Booking> findByShowID(Long id);
    List<Booking>findByBookingStatus(BookingStatus bookingStatus);
}
