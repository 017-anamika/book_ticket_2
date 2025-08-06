package com.anamika.book_ticket.Repository;

import com.anamika.book_ticket.Entity.showEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<showEntity, Long> {
    Optional<List<showEntity>> findByMovie_Id(Long movieId);

    Optional<List<showEntity>> findByTheater_Id(Long theaterId);
//    Optional<List<Show>>findByShow_Id(Long showId);
}
