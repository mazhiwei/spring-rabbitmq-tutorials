package org.mzw.rabbitmq.tutorials.conf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryConfig {
  public static final String EMAIL_QUEUE = "retry.queue.email";
  public static final String DEAD_QUEUE = "retry.queue.dead";
  public static final String EMAIL_EXCHANGE = "retry.exchange.email";
  public static final String EMAIL_ROUTING_KEY = "email";
  private static final String DEAD_ROUTING_KEY = "dead";

  @Bean
  public Queue emailQueue() {
    Map<String, Object> args = new HashMap<>(2);
    args.put("x-dead-letter-exchange", EMAIL_EXCHANGE);
    args.put("x-dead-letter-routing-key", DEAD_ROUTING_KEY);
    return new Queue(EMAIL_QUEUE, true, false, false, args);
  }

  @Bean
  public DirectExchange emailExchage() {
    return new DirectExchange(EMAIL_EXCHANGE);
  }

  @Bean
  public Binding emaBinding(DirectExchange emailExchage, Queue emailQueue) {
    return BindingBuilder.bind(emailQueue).to(emailExchage)
        .with(EMAIL_ROUTING_KEY);
  }

  @Bean
  public Queue deadQueue() {
    return new Queue(DEAD_QUEUE, true);
  }

  @Bean
  public Binding deadBinding(DirectExchange emailExchage, Queue deadQueue) {
    return BindingBuilder.bind(deadQueue).to(emailExchage)
        .with(DEAD_ROUTING_KEY);
  }

}
