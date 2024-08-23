package murilo.barbosa.rabbitmq.example;

import java.time.LocalDate;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class BookDto {

    private int id;
    private String title;
    private String genre;
    private String director;
    private LocalDate releaseDate;
    private double rating;
    private String description;
}
