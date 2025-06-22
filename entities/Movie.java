package system_design.movie_ticket_system.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Movie {
    int id;
    String name;
    String description;
    List<String> casts;
}
