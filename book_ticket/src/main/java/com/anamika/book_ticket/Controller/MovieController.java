package com.anamika.book_ticket.Controller;


import com.anamika.book_ticket.DTO.MovieDTO;
import com.anamika.book_ticket.Entity.Movie;
import com.anamika.book_ticket.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addmovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.addMovie(movieDTO));
    }

    @GetMapping("/getallmovies")
    public ResponseEntity<List<Movie>>getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/getmoviesbygenre")
    public ResponseEntity<List<Movie>>getMovieByGenre(@RequestParam String genre)  {
        return ResponseEntity.ok(movieService.getMovieByGenre(genre));
    }

    @GetMapping("/getmoviesbylanguage")
    public ResponseEntity<List<Movie>> getMovieByLanguage(@RequestParam String language)  {
        return ResponseEntity.ok(movieService.getMovieByGenre(language));
    }

    @GetMapping("/getmoviesbytitle")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title)  {
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }

    @PutMapping("/updatemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.updateMovie(id,movieDTO));
    }

    @DeleteMapping("/deletemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }


}
