package com.anamika.book_ticket.Repository;

import com.anamika.book_ticket.Entity.Booking;
import com.anamika.book_ticket.Entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByShowEntity_Id(Long showEntityId);
    List<Booking>findByBookingStatus(BookingStatus bookingStatus);
    List<Booking> findByUser_Id(Long userId);

}
