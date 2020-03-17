package org.mzw.rabbitmq.tutorials.multiple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipleConfig {
  @Bean
  public Queue mutipleQueue() {
    return new Queue("tutorials.multiple", true);
  }
}
