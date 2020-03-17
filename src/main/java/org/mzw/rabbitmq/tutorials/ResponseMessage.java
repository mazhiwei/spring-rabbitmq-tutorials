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
}
