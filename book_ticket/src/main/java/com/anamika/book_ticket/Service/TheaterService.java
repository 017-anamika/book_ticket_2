package com.anamika.book_ticket.Service;

import com.anamika.book_ticket.DTO.TheaterDTO;
import com.anamika.book_ticket.Entity.Theater;
import com.anamika.book_ticket.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;
    public Theater addTheater(TheaterDTO theaterDTO){
        Theater theater = new Theater();
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        return theaterRepository.save(theater);
    }

    public List<Theater> getTheaterByLocation(String location){
        Optional<List<Theater>> listOfTheaterBox = theaterRepository.findByLocation(location);
        if(listOfTheaterBox.isPresent()){
            return listOfTheaterBox.get();
        }else throw new RuntimeException("No theaters found for the location: " + location);
    }

    public Theater updateTheater(Long id,TheaterDTO theaterDTO) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no theater with id: " + id));
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        return theaterRepository.save(theater);
    }

    public void deleteTheater(Long id){
        theaterRepository.deleteById(id);
    }


}

