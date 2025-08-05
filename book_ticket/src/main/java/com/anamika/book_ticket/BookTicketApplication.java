package com.anamika.book_ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.anamika.book_ticket.Repository")
@EntityScan(basePackages = "com.anamika.book_ticket.Entity")
@SpringBootApplication
public class BookTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTicketApplication.class, args);
	}

}
