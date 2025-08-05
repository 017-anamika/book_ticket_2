package com.anamika.book_ticket.Controller;

import com.anamika.book_ticket.DTO.ShowDTO;
import com.anamika.book_ticket.Entity.showEntity;
import com.anamika.book_ticket.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/createshow")
    public ResponseEntity<showEntity> createShow(@RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.createShow(showDTO));
    }

    @GetMapping("/getallshows")
    public ResponseEntity<List<showEntity>> getAllShows(){
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/getshowsbymovie/{id}")
    public ResponseEntity<List<showEntity>>getShowsByMovie(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowsByMovie(id));
    }

    @GetMapping("/getshowsbytheater/{id}")
    public ResponseEntity<List<showEntity>> getShowsByTheater(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowsByTheater(id));
    }

    @PutMapping("/shows/{id}")
    public ResponseEntity<showEntity> updateShow(@PathVariable Long id, @RequestBody ShowDTO showDTO) {
        return ResponseEntity.ok(showService.updateShow(id, showDTO));
    }

    @DeleteMapping("/deleteshow")
    public ResponseEntity<showEntity> deleteShow(@PathVariable Long id){
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }
}
