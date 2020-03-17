package org.mzw.rabbitmq.tutorials.direct;

import org.mzw.rabbitmq.tutorials.RoutingSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender implements RoutingSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(DirectSender.class);
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private DirectExchange direct;

  @Override
  public void send(String key, int id) {
    LOGGER.debug("send {}, key: {}.", id, key);
    rabbitTemplate.convertAndSend(direct.getName(), key, id);
  }

}
