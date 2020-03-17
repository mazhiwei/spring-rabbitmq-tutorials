package org.mzw.rabbitmq.tutorials;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestId {
  private static final AtomicInteger ID = new AtomicInteger(1000);

  public static int next() {
    return ID.getAndIncrement();
  }
}
