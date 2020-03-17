package org.mzw.rabbitmq.tutorials.fanout;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
  @Bean
  public FanoutExchange fanout() {
    return new FanoutExchange("tutorials.fanout");
  }

  @Bean
  public Queue fanoutQueue1() {
    return new AnonymousQueue();
  }

  @Bean
  public Queue fanoutQueue2() {
    return new AnonymousQueue();
  }

  @Bean
  public Binding binding1(FanoutExchange fanout, Queue fanoutQueue1) {
    return BindingBuilder.bind(fanoutQueue1).to(fanout);
  }

  @Bean
  public Binding binding2(FanoutExchange fanout, Queue fanoutQueue2) {
    return BindingBuilder.bind(fanoutQueue2).to(fanout);
  }
}
