package org.mzw.rabbitmq.tutorials.rpc;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RPCSender {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private DirectExchange rpcExchange;

  int start = 0;

  public void send() {
    System.out.println(" [x] Requesting fib(" + start + ")");
    Integer response = (Integer) rabbitTemplate.convertSendAndReceive(rpcExchange.getName(), "rpc", start++);
    System.out.println(" [.] Got '" + response + "'");
  }
}
