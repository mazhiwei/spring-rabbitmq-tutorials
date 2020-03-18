package org.mzw.rabbitmq.tutorials.conf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryConfig {
  // 转发器
  public static String NORMAL_EXCHANGE = "retry.exchange.normal";
  public static String RETRY_EXCHANGE = "retry.exchange.retry";
  public static String FILED_EXCHANGE = "retry.exchange.filed";
  // 队列
  public static String NORMAL_QUEUE = "retry.queue.normal";
  public static String RETRY_QUEUE = "retry.queue.retry";
  public static String FILED_QUEUE = "retry.queue.filed";

  @Bean
  public Queue normalQueue() {
    return new Queue(NORMAL_QUEUE, true);
  }

  @Bean
  public Queue retryQueue() {
    // 声明重试队列,重试队列比较特殊，需要设置两个参数
    Map<String, Object> arg = new HashMap<String, Object>();
    // 参数1：将消息发送到哪一个转发器
    arg.put("x-dead-letter-exchange", NORMAL_EXCHANGE);
    // 参数2：多长时间后发送
    arg.put("x-message-ttl", 10000);
    return new Queue(RETRY_QUEUE, true, false, false, arg);
  }

  @Bean
  public Queue failedQueue() {
    return new Queue(FILED_QUEUE, true);
  }

  @Bean
  public TopicExchange normalExchange() {
    return new TopicExchange(NORMAL_EXCHANGE);
  }

  @Bean
  public TopicExchange retryExchange() {
    return new TopicExchange(RETRY_EXCHANGE);
  }

  @Bean
  public TopicExchange failedExchange() {
    return new TopicExchange(FILED_EXCHANGE);
  }

  @Bean
  public Binding normalBinding(TopicExchange normalExchange,
      Queue normalQueue) {
    return BindingBuilder.bind(normalQueue).to(normalExchange).with("normal");
  }

  @Bean
  public Binding retryBinding(TopicExchange retryExchange, Queue retryQueue) {
    return BindingBuilder.bind(retryQueue).to(retryExchange).with("normal");
  }

  @Bean
  public Binding failedBinding(TopicExchange failedExchange,
      Queue failedQueue) {
    return BindingBuilder.bind(failedQueue).to(failedExchange).with("normal");
  }
}
