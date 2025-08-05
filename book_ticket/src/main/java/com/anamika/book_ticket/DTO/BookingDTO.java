package com.anamika.book_ticket.DTO;

import com.anamika.book_ticket.Entity.BookingStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDTO {
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private Double price;
    private List<String> seatNumbers;
    private BookingStatus bookingStatus;

    private Long userId;
    private Long showId;


    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getShowId() {
        return showId;
    }
    public void setShowId(Long showId) {
        this.showId = showId;
    }
    public List<String> getSeatNumbers() {
        return seatNumbers;
    }
    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }
    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}
