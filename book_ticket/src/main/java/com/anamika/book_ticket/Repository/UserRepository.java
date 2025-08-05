package com.anamika.book_ticket.Repository;

import com.anamika.book_ticket.Entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<userEntity, Long> {
    Optional<userEntity> findByUsername(String username);

}
