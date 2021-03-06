package org.mzw.rabbitmq.tutorials.multiple;

import org.mzw.rabbitmq.tutorials.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultipleSender implements Sender {
  private static final Logger LOGGER = LoggerFactory.getLogger(MultipleSender.class);
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private Queue mutipleQueue;

  @Override
  public void send(int id) {
    LOGGER.debug("send id: {}", id);
    rabbitTemplate.convertAndSend(mutipleQueue.getName(), id);
  }

}
