package org.mzw.rabbitmq.tutorials.sender;

import org.mzw.rabbitmq.tutorials.conf.RetryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetryPublisher {
  private static final Logger LOGGER = LoggerFactory
      .getLogger(RetryPublisher.class);
  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void send() {
    LOGGER.debug("send messages");
    String content = "retry message at " + System.currentTimeMillis();
    Message message = MessageBuilder.withBody(content.getBytes())
        .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
        .setContentType(MessageProperties.CONTENT_TYPE_BYTES).build();
    rabbitTemplate.convertAndSend(RetryConfig.EMAIL_EXCHANGE,
        RetryConfig.EMAIL_ROUTING_KEY, message);
  }
}