package system_design.movie_ticket_system.repository;

import system_design.movie_ticket_system.entities.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepository {
    List<Movie> movies;

    public MovieRepository() {
        movies = new ArrayList<>();
    }

    public Optional<Movie> findAllById(int id) {
        return movies.stream().filter(m -> m.getId() == id).findFirst();
    }

    public List<Movie> getAll() {
        return movies;
    }


    public Movie save(Movie movie) {
        movies.add(movie);
        return movie;
    }

    public void update(Movie movie) {
        for (Movie m : movies) {
            if (m.getId() == movie.getId()) {
                movies.remove(m);
                movies.add(movie);
            }
        }
    }
}
