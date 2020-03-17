package org.mzw.rabbitmq.tutorials;

public interface RoutingSender {
  void send(String key, int id);
}
