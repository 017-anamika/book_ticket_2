package com.anamika.book_ticket.Controller;

import com.anamika.book_ticket.DTO.BookingDTO;
import com.anamika.book_ticket.Entity.Booking;
import com.anamika.book_ticket.Entity.BookingStatus;
import com.anamika.book_ticket.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {


    private BookingService bookingService;

    public BookingController (BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/createbooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/getshowbookings/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id){

        return ResponseEntity.ok(bookingService.getShowBookings(id));
    }
    @GetMapping("/getuserbookings/{id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id){

        return ResponseEntity.ok(bookingService.getUserBookings(id));
    }

    @PutMapping("{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }

    @PutMapping("{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }


    @GetMapping("/getbookingsbystatus/{bookingStatus}")
    public ResponseEntity<List<Booking>>getBookingsByStatus(@PathVariable BookingStatus bookingStatus){
        return ResponseEntity.ok(bookingService.getBookingByStatus(bookingStatus));
    }
}
