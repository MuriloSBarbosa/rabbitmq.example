package murilo.barbosa.rabbitmq.example.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import murilo.barbosa.rabbitmq.example.BookDto;
import murilo.barbosa.rabbitmq.example.config.AmqpConfig;
import murilo.barbosa.rabbitmq.example.mapper.BookEntityMapper;
import murilo.barbosa.rabbitmq.example.repository.BookRepository;
import org.hibernate.internal.util.StringHelper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookListener {

    private final BookRepository bookRepository;
    private final BookEntityMapper mapper;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = AmqpConfig.BOOK_LOADER_QUEUE)
    public void receiveObject(Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        boolean multiple = false;
        boolean requeue = false; // no requeue, we will send to a DQL (Dead Letter Queue)
        try {
            log.info("Received: {}", deliveryTag);
            handleBook();
            var bookDto = objectMapper.readValue(message.getBody(), BookDto.class);
            if(StringHelper.isBlank(bookDto.getTitle())) {
                throw new RuntimeException("Title is blank");
            }
            bookRepository.save(mapper.toEntity(bookDto));
            channel.basicAck(deliveryTag, multiple);
        } catch (Exception e) {
            log.error("Error processing message: {}", deliveryTag);
            try {
                channel.basicNack(deliveryTag, multiple, requeue);
            } catch (IOException ioException) {
                log.error("Failed to nack message", ioException);
            }
        }
    }

    @Async
    public void handleBook() {
        // simulate delay
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
