package org.mzw.rabbitmq.tutorials.simple;

import org.mzw.rabbitmq.tutorials.RequestId;
import org.mzw.rabbitmq.tutorials.Sender;
import org.mzw.rabbitmq.tutorials.models.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
  @Autowired
  private Sender simpleSender;

  @GetMapping("/simple")
  public SendResponse send() {
    int id = RequestId.next();
    simpleSender.send(id);
    return SendResponse.of(id);
  }
}
