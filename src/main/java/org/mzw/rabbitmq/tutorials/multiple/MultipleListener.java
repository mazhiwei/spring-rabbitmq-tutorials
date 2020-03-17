package org.mzw.rabbitmq.tutorials.multiple;

import org.mzw.rabbitmq.tutorials.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MultipleListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(MultipleListener.class);

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

  private void receive(int receiver, Integer id) {
    String response = ResponseMessage.build(receiver, id);
    LOGGER.debug(response);
    ResponseMessage.put(id, response);
  }
}
