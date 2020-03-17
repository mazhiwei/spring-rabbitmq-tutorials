package org.mzw.rabbitmq.tutorials.topic;

import org.mzw.rabbitmq.tutorials.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(TopicListener.class);

  @RabbitListener(queues = "#{topicQueue1.name}")
  public void receive1(Integer id) {
    receive(id, 1);
  }

  @RabbitListener(queues = "#{topicQueue2.name}")
  public void receive2(Integer id) {
    receive(id, 2);
  }

  public void receive(Integer id, int receiver) {
    String message = ResponseMessage.build(TopicListener.class, id, receiver);
    LOGGER.debug(message);
    ResponseMessage.put(id, message);
  }
}
