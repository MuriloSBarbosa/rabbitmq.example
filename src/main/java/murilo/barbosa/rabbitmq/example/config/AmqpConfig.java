package murilo.barbosa.rabbitmq.example.config;


import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AmqpConfig {

    public static final String BOOK_LOADER_QUEUE = "books-loader-queue";
    public static final String BOOK_LOADER_DLQ_QUEUE = "books-loader-dead-letter-queue";

    private static final String BOOK_LOADER_DLX = "books-loader-dlx";
    private static final String BOOK_LOADER_DLQ_ROUTING_KEY = "books.loader.dlq";

    @Bean
    Queue bookQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", BOOK_LOADER_DLX);
        args.put("x-dead-letter-routing-key", BOOK_LOADER_DLQ_ROUTING_KEY);

        return new Queue(BOOK_LOADER_QUEUE, true, false, false, args);
    }

    @Bean
    Queue bookDlq() {
        return new Queue(BOOK_LOADER_DLQ_QUEUE, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
          ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // * https://www.rabbitmq.com/docs/confirms#acknowledgement-modes
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        // * https://www.rabbitmq.com/docs/confirms#channel-qos-prefetch
        // Prefetch count is like a slide windows of unacknowledged deliveries that are permitted on a channel
//        factory.setPrefetchCount(5);
//        factory.setConcurrentConsumers(2);
        return factory;
    }
}
