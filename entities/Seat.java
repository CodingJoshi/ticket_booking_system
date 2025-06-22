package system_design.movie_ticket_system.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Seat {
    int id;
    Screen screen; // one to one
    boolean isLocked = false;
    long ttl;
    boolean houseFull = false;
    boolean isBooked = false;
    int version = 0;
}
