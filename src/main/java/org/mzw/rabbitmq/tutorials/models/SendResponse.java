package org.mzw.rabbitmq.tutorials.models;

public class SendResponse {
  public static SendResponse of(int id) {
    return new SendResponse(id);
  }

  private int id;

  private SendResponse(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

}
