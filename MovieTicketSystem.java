package system_design.movie_ticket_system;

import system_design.movie_ticket_system.controller.MovieController;
import system_design.movie_ticket_system.controller.SeatBookingController;
import system_design.movie_ticket_system.entities.Movie;
import system_design.movie_ticket_system.entities.Screen;
import system_design.movie_ticket_system.entities.Seat;
import system_design.movie_ticket_system.repository.MovieRepository;
import system_design.movie_ticket_system.repository.SeatRepository;
import system_design.movie_ticket_system.sevices.MovieService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MovieTicketSystem {
    public static void main(String[] args) throws InterruptedException {
        MovieRepository movieRepository = new MovieRepository();
        MovieService movieService = new MovieService(movieRepository);
        MovieController movieController = new MovieController(movieService);
        Movie M1 = new Movie(1, "M1", "description", List.of("C1", "C2"));
        Movie M2 = new Movie(2, "M2", "description", List.of("C1", "C2"));
        Movie M3 = new Movie(3, "M3", "description", List.of("C1", "C2"));
        Movie M4 = new Movie(4, "M4", "description", List.of("C1", "C2"));
        movieController.addMovie(M1);
        movieController.addMovie(M2);
        movieController.addMovie(M3);
        movieController.addMovie(M4);

        Screen pvrScreen = new Screen(1, "PVR", M1);
        Screen InoxScreen = new Screen(2, "INOX", M1);
        Screen V3SMalScreen = new Screen(3, "V3SMall", M1);

        SeatRepository seatRepository = new SeatRepository();
        SeatBookingController seatBookingController = new SeatBookingController(seatRepository);
        for (int i = 0; i < 100; i++) {
            seatBookingController.addSeat(Seat.builder()
                    .id(i)
                    .screen(pvrScreen)
                    .build());
        }

        ExecutorService executor = Executors.newFixedThreadPool(4);


        for (int i = 0; i < 10; i++) {
            executor.submit(() -> seatBookingController.bookSeat(List.of(1)));
        }

        executor.shutdown();

        executor.awaitTermination(5, TimeUnit.SECONDS);


        for (Seat seat : seatRepository.findAllById(List.of(1))) {
            System.out.println(seat.getId() + " " + seat.isLocked() + " " + seat.getTtl() + " " + seat.getVersion());
        }
    }
}
