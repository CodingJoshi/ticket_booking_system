package system_design.movie_ticket_system.controller;

import system_design.movie_ticket_system.entities.Seat;
import system_design.movie_ticket_system.repository.SeatRepository;

import java.util.List;

public class SeatBookingController {
    SeatRepository repository;

    public SeatBookingController(SeatRepository repository) {
        this.repository = repository;
    }

    public void bookSeat(List<Integer> seatIds) {
        List<Seat> seatList = repository.findAllById(seatIds);
        checkSeatsValidity(seatList);// should not be already locked and booked
        for (Seat seat : seatList) {
            // update seats set is_locked = true
            // where seatId = id and version = :version and ttl < now()
            int rowUpdated = repository.bookSeat(seat.getId(), seat.getVersion());
            if (rowUpdated == 0) {
                System.out.println(Thread.currentThread().getName() + "EXCEPTION");
            } else {
                System.out.println(Thread.currentThread().getName() + "Success");
            }
        }
    }

    private void checkSeatsValidity(List<Seat> seatList) {
        for (Seat seat : seatList) {
            if (seat.isBooked() || (seat.isLocked() && seat.getTtl() > System.currentTimeMillis())) {
                System.out.println("EXCEPTION: Seat is already booked");
            }
        }
    }

    public List<Seat> seatList(int screen_Id) {
        // Select * from seats where screen_id = :screenId;
        return repository.findAllByScreenId(screen_Id);
    }

    public void addSeat(Seat seat) {
        repository.save(seat);
    }
}
