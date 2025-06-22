package system_design.movie_ticket_system.controller;

import system_design.movie_ticket_system.entities.Movie;
import system_design.movie_ticket_system.sevices.MovieService;

import java.util.List;

// /api/v1/movie
public class MovieController {
    MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    //    /list
    public List<Movie> list() {
        return service.getAllMovies();
    }

    public Movie addMovie(Movie movie) {
        return service.addMovie(movie);
    }

}
