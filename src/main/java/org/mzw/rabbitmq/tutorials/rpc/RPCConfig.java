package org.mzw.rabbitmq.tutorials.rpc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RPCConfig {
  @Bean
  public Queue rpcQueue() {
    return new Queue("tutorials.rpc.requests");
  }

  @Bean
  public DirectExchange rpcExchange() {
    return new DirectExchange("tutorials.rpc");
  }

  @Bean
  public Binding binding(DirectExchange rpcExchange, Queue rpcQueue) {
    return BindingBuilder.bind(rpcQueue).to(rpcExchange).with("rpc");
  }
}
