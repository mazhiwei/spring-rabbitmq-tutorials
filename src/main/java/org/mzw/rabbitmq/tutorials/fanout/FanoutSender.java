package org.mzw.rabbitmq.tutorials.fanout;

import org.mzw.rabbitmq.tutorials.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender implements Sender {
  private static final Logger LOGGER = LoggerFactory.getLogger(FanoutSender.class);
  @Autowired
  private RabbitTemplate template;
  @Autowired
  private FanoutExchange fanout;

  @Override
  public void send(int id) {
    LOGGER.debug("send id: {}", id);
    template.convertAndSend(fanout.getName(), "", id);
  }

}
