package org.mzw.rabbitmq.tutorials.multiple;

import org.mzw.rabbitmq.tutorials.RequestId;
import org.mzw.rabbitmq.tutorials.Sender;
import org.mzw.rabbitmq.tutorials.models.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultipleController {
  @Autowired
  private Sender multipleSender;

  @GetMapping("/multiple")
  public SendResponse send() {
    int id = RequestId.next();
    multipleSender.send(id);
    return SendResponse.of(id);
  }
}
