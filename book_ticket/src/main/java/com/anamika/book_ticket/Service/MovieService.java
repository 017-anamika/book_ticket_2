package com.anamika.book_ticket.Service;

import com.anamika.book_ticket.DTO.MovieDTO;
import com.anamika.book_ticket.Entity.Movie;
import com.anamika.book_ticket.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService   {
    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setGenre(movieDTO.getGenre());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());

       return  movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie>getMovieByGenre(String genre){
        Optional<List<Movie>> listofMovieBox =  movieRepository.findByGenre(genre);
        if(listofMovieBox.isPresent()){
            return listofMovieBox.get();
        }
        else{
            throw new RuntimeException("No Movie found for genre " + genre);
        }
    }

    public List<Movie>getMoviesByLanguage(String language){
        Optional<List<Movie>> listofMovieBox =  movieRepository.findByLanguage(language);
        if(listofMovieBox.isPresent()){
            return listofMovieBox.get();
        }
        else{
            throw new RuntimeException("No Movie found of language " + language);
        }
    }

    public Movie getMovieByTitle(String title){
       Optional<Movie>movieBox =  movieRepository.findByName(title);
       if(movieBox.isPresent()){
           return movieBox.get();
       }
       else{
           throw new RuntimeException("No Movie found for title " + title);
       }
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No movie Found for id" + id));

        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setGenre(movieDTO.getGenre());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }


}
