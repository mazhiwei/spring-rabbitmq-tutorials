package org.mzw.rabbitmq.tutorials.models;

public class TaskResponse {
  private boolean ok;
  private String content;

  public boolean isOk() {
    return ok;
  }

  public void setOk(boolean ok) {
    this.ok = ok;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
