package murilo.barbosa.rabbitmq.example.amqp;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import murilo.barbosa.rabbitmq.example.BookDto;
import murilo.barbosa.rabbitmq.example.mapper.BookEntityMapper;
import murilo.barbosa.rabbitmq.example.repository.BookRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Receiver {

    private final BookRepository bookRepository;
    private final BookEntityMapper mapper;

    @RabbitListener(queues = AmqpConfig.QUEUE_NAME)
    public void receiveObject(final BookDto bookDto, Message message, Channel channel) {
        try {
            Thread.sleep(3000);
            log.info("Received: {}", bookDto);
            bookRepository.save(mapper.toEntity(bookDto));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("Error processing message: {}", bookDto, e);
            try {
                // ✅ Manually requeue the message
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ioException) {
                log.error("Failed to nack message", ioException);
            }
        }

        System.out.println("Waiting for messages");
    }

}
