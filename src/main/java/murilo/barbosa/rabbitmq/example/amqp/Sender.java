package murilo.barbosa.rabbitmq.example.amqp;

import com.github.javafaker.Faker;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import murilo.barbosa.rabbitmq.example.BookDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RequiredArgsConstructor
@Slf4j
public class Sender {

    private final RabbitTemplate rabbitTemplate;

    //    @Scheduled(fixedDelay = 5000, initialDelay = 500)
    public void sendNewMovie() {
        var movie = randomMovie();
        log.info("Sending movie: {}", movie);
        rabbitTemplate.convertAndSend(AmqpConfig.QUEUE_NAME, movie);
    }

    private BookDto randomMovie() {
        Faker faker = new Faker();
        var movieDto = new BookDto();
        movieDto.setTitle(faker.book().title());
        movieDto.setDirector(faker.book().author());
        movieDto.setGenre(faker.book().genre());
        long date = faker.date().birthday().getTime();
        movieDto.setReleaseDate(LocalDate.from(
              LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault())));

        return movieDto;
    }

}
