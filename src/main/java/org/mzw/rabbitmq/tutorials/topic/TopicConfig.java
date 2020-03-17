package org.mzw.rabbitmq.tutorials.topic;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
  @Bean
  public TopicExchange topic() {
    return new TopicExchange("tutorials.topic");
  }

  @Bean
  public Queue topicQueue1() {
    return new AnonymousQueue();
  }

  @Bean
  public Queue topicQueue2() {
    return new AnonymousQueue();
  }

  @Bean
  public Binding topicBinding1a(TopicExchange topic, Queue topicQueue1) {
    return BindingBuilder.bind(topicQueue1).to(topic).with("*.orange.*");
  }

  @Bean
  public Binding topicBinding1b(TopicExchange topic, Queue topicQueue1) {
    return BindingBuilder.bind(topicQueue1).to(topic).with("*.*.rabbit");
  }

  @Bean
  public Binding topicBinding2a(TopicExchange topic, Queue topicQueue2) {
    return BindingBuilder.bind(topicQueue2).to(topic).with("lazy.#");
  }
}
