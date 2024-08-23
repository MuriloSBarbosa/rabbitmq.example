package murilo.barbosa.rabbitmq.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Receiver {

    @RabbitListener(queues = AmqpConfig.queueName)
    public void receiveObject(final BookDto bookDto) {
        log.info("Received: {}", bookDto);
    }
}
