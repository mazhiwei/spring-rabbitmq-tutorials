package org.mzw.rabbitmq.tutorials.topic;

import org.mzw.rabbitmq.tutorials.RoutingSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender implements RoutingSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(TopicSender.class);
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private TopicExchange topic;

  @Override
  public void send(String key, int id) {
    LOGGER.debug("send id: {}, key: {}", id, key);
    rabbitTemplate.convertAndSend(topic.getName(), key, id);
  }
}
