package com.anamika.book_ticket.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class showEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime showTime;
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theater_id",nullable = false)
    private Theater theater;


    @OneToMany(mappedBy = "showEntity", fetch = FetchType.LAZY)
    private List<Booking> bookings;

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void setTheater(Theater theater) {
        this.theater = theater;
    }
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }
    public Double getPrice() {
        return price;
    }
    public Movie getMovie() {
        return movie;
    }
    public Theater getTheater() {
        return theater;
    }
    public List<Booking> getBookings() {
        return bookings;
    }


}


