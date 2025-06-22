package system_design.movie_ticket_system.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Screen {
    int id;
    String name;
    Movie movie; // one to one
}
