package com.anamika.book_ticket.Controller;


import com.anamika.book_ticket.DTO.TheaterDTO;
import com.anamika.book_ticket.Entity.Theater;
import com.anamika.book_ticket.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {
    private TheaterService theaterService;
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("/addtheater")
    public ResponseEntity<Theater>addTheater(@RequestBody TheaterDTO theaterDTO) {
        return ResponseEntity.ok(theaterService.addTheater(theaterDTO));
    }

    @PostMapping("gettheaterbylocation")
    public ResponseEntity<List<Theater>>getTheaterByLocation(@RequestParam String location) {
        return ResponseEntity.ok(theaterService.getTheaterByLocation(location));
    }

    @PutMapping("/updatetheater")
    @PreAuthorize("hashRole('ADMIN')")
    public ResponseEntity<Theater>updateTheater(@RequestBody Long id, TheaterDTO theaterDTO) {
        return ResponseEntity.ok(theaterService.updateTheater(id,theaterDTO));
    }

    @DeleteMapping("/deletetheater/{id}")
    @PreAuthorize("hashRole('ADMIN')")
    public ResponseEntity<Void>deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.ok().build();
    }

}
