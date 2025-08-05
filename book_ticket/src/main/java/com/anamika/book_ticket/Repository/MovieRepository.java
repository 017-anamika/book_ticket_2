package com.anamika.book_ticket.Repository;

import com.anamika.book_ticket.Entity.Movie;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MovieRepository  extends JpaRepository<Movie, Long> {
     Optional<List<Movie>> findByGenre(String genre);
    Optional<List<Movie>> findByLanguage(String language);
    Optional<Movie>findByName(String title);

    List<Movie> id(Long id);
}
