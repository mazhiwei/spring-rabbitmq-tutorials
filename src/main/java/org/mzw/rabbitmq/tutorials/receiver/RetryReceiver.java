package org.mzw.rabbitmq.tutorials.receiver;

import java.io.IOException;

import org.mzw.rabbitmq.tutorials.conf.RetryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RetryReceiver {
  private static final Logger LOGGER = LoggerFactory
      .getLogger(RetryReceiver.class);

  @RabbitListener(queues = RetryConfig.EMAIL_QUEUE)
  public void processEmail(Message message) throws IOException {
    LOGGER.debug("process email {}", message);
    work(message.getBody());
  }

  @RabbitListener(queues = RetryConfig.DEAD_QUEUE)
  public void processDeadEmail(Message message) throws IOException {
    LOGGER.debug("process dead email {}", message);
  }

  private void work(byte[] body) throws IOException {
    String message = new String(body, "utf-8");
    LOGGER.debug("work on {}", message);
    throw new RuntimeException("fake exception");
  }

}