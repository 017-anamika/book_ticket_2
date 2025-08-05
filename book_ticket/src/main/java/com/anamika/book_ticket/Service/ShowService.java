package com.anamika.book_ticket.Service;


import com.anamika.book_ticket.DTO.ShowDTO;
import com.anamika.book_ticket.Entity.Booking;
import com.anamika.book_ticket.Entity.Movie;
import com.anamika.book_ticket.Entity.showEntity;
import com.anamika.book_ticket.Entity.Theater;
import com.anamika.book_ticket.Repository.MovieRepository;
import com.anamika.book_ticket.Repository.ShowRepository;
import com.anamika.book_ticket.Repository.TheaterRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public showEntity createShow(ShowDTO showDTO){
        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()->new RuntimeException("No movie Found"));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(()->new RuntimeException(("No theater Found for id"+ showDTO.getTheaterId())));
        showEntity showEntity = new showEntity();
        showEntity.setMovie(movie);
        showEntity.setTheater(theater);
        showEntity.setShowTime(showDTO.getShowTime());
        showEntity.setPrice(showDTO.getPrice());
        return showRepository.save(showEntity);
    }

    public List<showEntity> getAllShows(){
        return showRepository.findAll();
    }

    public List<showEntity>getShowsByMovie(Long movieId){
        Optional<List<showEntity>>showListBox = showRepository.findByMovieId(movieId);
        if(showListBox.isPresent()){
            return showListBox.get();
        }
        else throw new RuntimeException("No Show Found for this movie");
    }

    public List<showEntity>getShowsByTheater(Long theaterid){
        Optional<List<showEntity>>showListBox = showRepository.findByMovieId(theaterid);
        if(showListBox.isPresent()){
            return showListBox.get();
        }
        else throw new RuntimeException("No Show Found for this theater");
    }

    public showEntity updateShow(Long id, ShowDTO showDTO) {
        showEntity showEntity = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No movie found for id" + showDTO.getMovieId()));

        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("No movie Found"));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(() -> new RuntimeException(("No theater Found for id" + showDTO.getTheaterId())));
        showEntity.setMovie(movie);
        showEntity.setTheater(theater);
        showEntity.setShowTime(showDTO.getShowTime());
        showEntity.setPrice(showDTO.getPrice());
        return showRepository.save(showEntity);
    }

    public void deleteShow(Long id) {
        if(!showRepository.existsById(id)){
            throw new RuntimeException("No Show found for id" + id);
        }
        List<Booking>bookings = showRepository.findById(id).get().getBookings();
        if(!bookings.isEmpty()) {
            throw new RuntimeException("Cann't delete show with existing id" + id);
        }
        showRepository.deleteById(id);
    }



}
