package org.mzw.rabbitmq.tutorials.simple;

import org.mzw.rabbitmq.tutorials.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

  @RabbitHandler
  @RabbitListener(queues = "#{simpleQueue.name}")
  public void receive(Integer id) {
    String message = ResponseMessage.build(SimpleListener.class, id);
    LOGGER.debug(message);
    ResponseMessage.put(id, message);
  }
}
