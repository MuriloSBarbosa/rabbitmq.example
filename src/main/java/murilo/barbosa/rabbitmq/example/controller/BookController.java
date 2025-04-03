package murilo.barbosa.rabbitmq.example.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import murilo.barbosa.rabbitmq.example.BookEntity;
import murilo.barbosa.rabbitmq.example.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    public List<BookEntity> getBooks() {
        return bookRepository.findAll();
    }

}
