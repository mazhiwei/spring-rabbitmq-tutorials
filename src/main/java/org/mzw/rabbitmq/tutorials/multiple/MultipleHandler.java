package org.mzw.rabbitmq.tutorials.multiple;

import org.mzw.rabbitmq.tutorials.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MultipleHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(MultipleHandler.class);

  @RabbitHandler
  @RabbitListener(queues = "#{mutipleQueue.name}")
  public void receive1(Integer id) {
    receive(1, id);
  }

  @RabbitHandler
  @RabbitListener(queues = "#{mutipleQueue.name}")
  public void receive2(Integer id) {
    receive(2, id);
  }

  private void receive(int handlerId, Integer id) {
    LOGGER.debug("received id: {}, handler id: {}.", id, handlerId);
    ResponseMessage.put(id, "Multiple Handler " + handlerId + ".");
  }
}
