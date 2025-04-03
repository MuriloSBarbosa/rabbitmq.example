package murilo.barbosa.rabbitmq.example.repository;

import murilo.barbosa.rabbitmq.example.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, String> {

}
