package org.mzw.rabbitmq.tutorials.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {
  @Bean
  public Queue simpleQueue() {
    return new Queue("tutorials.simple");
  }
}
