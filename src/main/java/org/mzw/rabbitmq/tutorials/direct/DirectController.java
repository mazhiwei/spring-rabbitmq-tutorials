package org.mzw.rabbitmq.tutorials.direct;

import org.mzw.rabbitmq.tutorials.RequestId;
import org.mzw.rabbitmq.tutorials.RoutingSender;
import org.mzw.rabbitmq.tutorials.models.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectController {
  @Autowired
  private RoutingSender directSender;

  @GetMapping("/direct")
  public SendResponse send(@RequestParam DirectRoute key) {
    int id = RequestId.next();
    directSender.send(key.toString(), id);
    return SendResponse.of(id);
  }
}
