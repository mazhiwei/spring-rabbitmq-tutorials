package org.mzw.rabbitmq.tutorials.simple;

import org.mzw.rabbitmq.tutorials.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleSender implements Sender {
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSender.class);
  @Autowired
  private RabbitTemplate rabbitTemplate;
  @Autowired
  private Queue simpleQueue;

  @Override
  public void send(int id) {
    LOGGER.debug("send id: {}", id);
    rabbitTemplate.convertAndSend(simpleQueue.getName(), id);
  }

}
