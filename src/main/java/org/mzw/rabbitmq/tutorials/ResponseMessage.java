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

  private static StringBuilder initializeBuilder(Class<?> clazz, Integer id) {
    StringBuilder builder = new StringBuilder(64);
    builder.append(clazz.getSimpleName()).append(" received request ").append(id);
    return builder;
  }

  public static String build(Class<?> clazz, Integer id) {
    return initializeBuilder(clazz, id).toString();
  }

  public static String build(Class<?> clazz, Integer id, int receiver) {
    StringBuilder builder = initializeBuilder(clazz, id);
    builder.append(", receiver: ").append(receiver);
    return builder.toString();
  }
}
