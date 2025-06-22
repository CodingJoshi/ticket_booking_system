package system_design.movie_ticket_system.repository;

import system_design.movie_ticket_system.entities.Seat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SeatRepository {
    List<Seat> seatList;

    public SeatRepository() {
        seatList = new CopyOnWriteArrayList<>();
    }

    void addSeat(Seat seat) {
        seatList.add(seat);
    }

    public List<Seat> findAllById(List<Integer> seatIds) {
        return seatList.stream().filter(seat -> seatIds.contains(seat.getId())).toList();
    }

    public Seat save(Seat seat) {
        seatList.add(seat);
        return seat;
    }

    public List<Seat> findAllByScreenId(int screenId) {
        return seatList.stream().filter(seat -> seat.getScreen().getId() == screenId).toList();
    }

    public synchronized int bookSeat(int seatId, int version) {
        // update seats set is_locked = true, version :version + 1
        // where seatId = id and version = :version and ttl < now() lock for update
        int cnt = 0;
        for (Seat seat : seatList) {
            if ((seat.getId() == seatId) && (seat.getVersion() == version) && !seat.isLocked()) {
                seat.setLocked(true);
                seat.setTtl(System.currentTimeMillis());
                seat.setVersion(seat.getVersion() + 1);
                cnt++;
            }
        }
        return cnt;
    }
}
