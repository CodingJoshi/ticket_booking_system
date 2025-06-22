package system_design.movie_ticket_system.sevices;

import system_design.movie_ticket_system.entities.Movie;
import system_design.movie_ticket_system.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

public class MovieService {
    MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getAllMovies() {
        return repository.getAll();
    }

    public Movie addMovie(Movie movie) {
        return repository.save(movie);
    }

    public Movie getMovie(int movie_id) {
        Optional<Movie> movieOptional = repository.findAllById(movie_id);
        return movieOptional.orElse(null);
    }

}
