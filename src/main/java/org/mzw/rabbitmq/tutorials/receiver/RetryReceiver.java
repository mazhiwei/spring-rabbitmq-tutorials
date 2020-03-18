package org.mzw.rabbitmq.tutorials.receiver;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.mzw.rabbitmq.tutorials.conf.RetryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetryReceiver {
  private static final Logger LOGGER = LoggerFactory
      .getLogger(RetryReceiver.class);
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @RabbitListener(queues = "#{normalQueue.name}")
  public void receiver(Message message) throws IOException, TimeoutException {
    try {
      work(message.getBody());
    } catch (Exception e) {
      LOGGER.warn("failed to process message: {}", e.getMessage());
      retry(message);
    }
  }

  private void work(byte[] body) throws IOException {
    String message = new String(body, "utf-8");
    LOGGER.debug("work on {}", message);
    throw new RuntimeException("fake exception");
  }

  private void retry(Message message) {
    long retryCount = getRetryCount(message.getMessageProperties());
    String exchange;
    if (retryCount >= 3) {
      LOGGER.info("send to failed exchange");
      exchange = RetryConfig.FILED_EXCHANGE;
    } else {
      LOGGER.info("retry count {}, send to retry exchange", retryCount);
      exchange = RetryConfig.RETRY_EXCHANGE;
    }
    rabbitTemplate.convertAndSend(exchange, "normal", message);
  }

  /**
   * get retry count from headers
   * 
   * @param properties
   * @return
   */
  @SuppressWarnings("unchecked")
  public long getRetryCount(MessageProperties properties) {
    long retryCount = 0L;
    Map<String, Object> header = properties.getHeaders();
    if (header != null && header.containsKey("x-death")) {
      List<Map<String, Object>> deaths = (List<Map<String, Object>>) header
          .get("x-death");
      if (deaths.size() > 0) {
        Map<String, Object> death = deaths.get(0);
        retryCount = (Long) death.get("count");
      }
    }
    return retryCount;
  }
}