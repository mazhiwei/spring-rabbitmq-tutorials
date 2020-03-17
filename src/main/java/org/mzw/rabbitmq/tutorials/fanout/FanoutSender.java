package org.mzw.rabbitmq.tutorials.fanout;

import org.mzw.rabbitmq.tutorials.Sender;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender implements Sender {
  @Autowired
  private RabbitTemplate template;
  @Autowired
  private FanoutExchange fanout;

  @Override
  public void send(int id) {
    template.convertAndSend(fanout.getName(), "", id);
  }

}
