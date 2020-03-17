package org.mzw.rabbitmq.tutorials.fanout;

import org.mzw.rabbitmq.tutorials.RequestId;
import org.mzw.rabbitmq.tutorials.Sender;
import org.mzw.rabbitmq.tutorials.models.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanoutController {
  @Autowired
  private Sender fanoutSender;

  @GetMapping("/fanout-send")
  public SendResponse fanout() {
    int id = RequestId.next();
    fanoutSender.send(id);
    return SendResponse.of(id);
  }
}
