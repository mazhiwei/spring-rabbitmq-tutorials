package org.mzw.rabbitmq.tutorials.direct;

import org.mzw.rabbitmq.tutorials.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(DirectListener.class);

  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive1(Integer id) {
    receive(id, 1);
  }

  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  public void receive2(Integer id) {
    receive(id, 2);
  }

  public void receive(Integer id, int receiver) {
    String message = ResponseMessage.build(DirectListener.class, id, receiver);
    LOGGER.debug(message);
    ResponseMessage.put(id, message);
  }
}
