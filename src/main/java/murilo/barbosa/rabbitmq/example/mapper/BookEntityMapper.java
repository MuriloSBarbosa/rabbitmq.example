package murilo.barbosa.rabbitmq.example.mapper;

import murilo.barbosa.rabbitmq.example.BookDto;
import murilo.barbosa.rabbitmq.example.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface BookEntityMapper {

    BookEntity toEntity(BookDto bookDto);
}
