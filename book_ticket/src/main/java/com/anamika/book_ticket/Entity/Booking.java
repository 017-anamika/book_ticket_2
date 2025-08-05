package com.anamika.book_ticket.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private Double price;
    private BookingStatus bookingStatus;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "booking_seat_numbers")
    private List<String> seatNumbers;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id",nullable = false)
    private userEntity user;


    public userEntity getUser() {
            return user;
    }
    public void setUser(userEntity user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id",nullable = false)
    private showEntity showEntity;



    public showEntity getShowEntity() {
        return showEntity;
    }
    public void setShowEntity(showEntity showEntity) {
        this.showEntity = showEntity;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public List<String> getSeatNumbers() {
        return seatNumbers;
    }
    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }


}
