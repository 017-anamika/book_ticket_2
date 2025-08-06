package com.anamika.book_ticket.Repository;

import com.anamika.book_ticket.Entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository  extends JpaRepository<Theater, Long> {

    Optional<List<Theater>> findByTheaterLocation(String location);
}
