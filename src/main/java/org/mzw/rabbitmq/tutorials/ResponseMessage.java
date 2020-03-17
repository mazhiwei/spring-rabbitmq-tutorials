package org.mzw.rabbitmq.tutorials;

import java.util.concurrent.ConcurrentHashMap;

public class ResponseMessage {
  private static final ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

  public static String getAndRemove(Integer key) {
    return map.remove(key);
  }

  public static void put(Integer key, String value) {
    map.put(key, value);
  }

  public static String build(int receiver, Integer id) {
    StringBuilder builder = new StringBuilder(64);
    builder.append("receiver: ").append(receiver).append(" received request ").append(id);
    return builder.toString();
  }
}
