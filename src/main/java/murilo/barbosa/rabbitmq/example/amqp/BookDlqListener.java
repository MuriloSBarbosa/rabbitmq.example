package murilo.barbosa.rabbitmq.example.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import murilo.barbosa.rabbitmq.example.config.AmqpConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookDlqListener {

    @RabbitListener(queues = AmqpConfig.BOOK_LOADER_DLQ_QUEUE)
    public void handleDeadLetter(Message message) {
        log.info("☠️  Dead letter received: consumer {}, messageId {}, body {}",
              message.getMessageProperties().getConsumerTag(),
              message.getMessageProperties().getMessageId(),
              new String(message.getBody()));
    }
}
