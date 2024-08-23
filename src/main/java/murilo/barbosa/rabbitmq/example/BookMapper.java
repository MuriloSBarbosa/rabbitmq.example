package murilo.barbosa.rabbitmq.example;

import java.awt.print.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}
