package org.mzw.rabbitmq.tutorials.direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class DirectListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(DirectListener.class);

  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
//  @RabbitHandler
  public void receive1(String in) throws InterruptedException {
    receive(in, 1);
  }

  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
//  @RabbitHandler
  public void receive2(String in) throws InterruptedException {
    receive(in, 2);
  }

  public void receive(String in, int receiver) throws InterruptedException {
    LOGGER.debug("receive {} in {}.", in, receiver);
    StopWatch watch = new StopWatch();
    watch.start();
    System.out.println("direct instance " + receiver + " [x] Received '" + in + "'");
    doWork(in);
    watch.stop();
    System.out.println("direct instance " + receiver + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
  }

  private void doWork(String in) throws InterruptedException {
    for (char ch : in.toCharArray()) {
      if (ch == '.') {
        Thread.sleep(1000);
      }
    }
  }
}
