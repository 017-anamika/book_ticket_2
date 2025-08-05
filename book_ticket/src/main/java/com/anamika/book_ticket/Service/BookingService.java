package com.anamika.book_ticket.Service;


import com.anamika.book_ticket.DTO.BookingDTO;
import com.anamika.book_ticket.Entity.Booking;
import com.anamika.book_ticket.Entity.BookingStatus;
import com.anamika.book_ticket.Entity.showEntity;
import com.anamika.book_ticket.Entity.userEntity;
import com.anamika.book_ticket.Repository.BookingRepository;
import com.anamika.book_ticket.Repository.ShowRepository;
import com.anamika.book_ticket.Repository.UserRepository;
import lombok.Data;
//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Service
public class BookingService {


    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
    }

    private ShowRepository showRepository;
    private UserRepository userRepository;

    public Booking createBooking(BookingDTO bookingDTO){
        showEntity showEntity = showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(()->new RuntimeException("Show not found"));

        if(!isSeatsAvailable(showEntity.getId(),bookingDTO.getNumberOfSeats())){
            throw new RuntimeException("Not Enough Seats available");
        }
        if(bookingDTO.getSeatNumbers().size()!= bookingDTO.getNumberOfSeats()){
            throw new RuntimeException("Seat number and number of Seats must be equal");
        }
        validateDuplicateSeats(showEntity.getId(),bookingDTO.getSeatNumbers());

        userEntity userr = userRepository.findById(bookingDTO.getUserId())
                        .orElseThrow(()-> new RuntimeException("User not found"));
        Booking booking = new Booking();
        booking.setShowEntity(showEntity);
        booking.setUser(userr);
        booking.setPrice(calculateTotalAmount(showEntity.getPrice(),bookingDTO.getNumberOfSeats()));
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setSeatNumbers(bookingDTO.getSeatNumbers());
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);
        bookingRepository.save(booking);
        return bookingRepository.save(booking);
    }

    public boolean isSeatsAvailable(Long showId, Integer numberOfSeats){
        showEntity showEntity = showRepository.findById(showId)
                .orElseThrow(()->new RuntimeException("Show not found"));
        int bookedSeats = showEntity.getBookings().stream()
                .filter(booking->booking.getBookingStatus() != BookingStatus.CANCELLED )
                .mapToInt(Booking::getNumberOfSeats)
                .sum();
        return (showEntity.getTheater().getTheaterCapacity()-bookedSeats)>=numberOfSeats;

    }

    public void validateDuplicateSeats(Long showId, List<String> seatNumbers){

        showEntity showEntity = showRepository.findById(showId)
                .orElseThrow(()->new RuntimeException("Show not found"));

        Set<String> occupiedSeats = showEntity.getBookings().stream()
                .filter(b->b.getBookingStatus()!=BookingStatus.CANCELLED)
                .flatMap(b->b.getSeatNumbers().stream())
                .collect(Collectors.toSet());

        List<String>duplicateSeats = seatNumbers.stream()
                .filter(occupiedSeats::contains)
                .collect(Collectors.toList());

        if(!duplicateSeats.isEmpty()){
            throw new RuntimeException("Seats are already booked");
        }

    }


    public Double calculateTotalAmount(Double price, Integer numberOfSeats){
        return price * numberOfSeats;
    }

    public List<Booking> getUserBookings(Long userId){
        return bookingRepository.findByUserID(userId);
    }

    public List<Booking> getShowBookings(Long showId){
        return bookingRepository.findByShowID(showId);
    }

    public Booking confirmBooking(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()->new RuntimeException("Booking not found"));

        if(booking.getBookingStatus()!=BookingStatus.PENDING){
            throw new RuntimeException("Booking is not PENDING");
        }
//        Payment process
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }
    public Booking cancelBooking(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()->new RuntimeException("Booking not found"));

        validateCancelation(booking);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    public void validateCancelation(Booking booking){
        LocalDateTime showTime = booking.getShowEntity().getShowTime();
        LocalDateTime deadlineTime= showTime.minusHours(2);
        if(LocalDateTime.now().isAfter(deadlineTime)){
           throw new RuntimeException("can not cancel booking");
        }
        if(booking.getBookingStatus()==BookingStatus.CANCELLED){
            throw new RuntimeException("booking Already been cancelled");
        }
    }

    public List<Booking> getBookingByStatus(BookingStatus status){
        return bookingRepository.findByBookingStatus(status);
    }



}
