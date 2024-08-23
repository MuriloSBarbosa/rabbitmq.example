package murilo.barbosa.rabbitmq.example;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AmqpConfig {

    public static final String queueName = "MURILO-TESTING";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
