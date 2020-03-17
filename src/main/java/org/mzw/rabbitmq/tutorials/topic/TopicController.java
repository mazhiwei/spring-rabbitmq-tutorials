package org.mzw.rabbitmq.tutorials.topic;

import org.mzw.rabbitmq.tutorials.RequestId;
import org.mzw.rabbitmq.tutorials.RoutingSender;
import org.mzw.rabbitmq.tutorials.models.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
  @Autowired
  private RoutingSender topicSender;

  @GetMapping("/topic")
  public SendResponse send(@RequestParam boolean quick, @RequestParam Color color, @RequestParam Animal animal) {
    StringBuilder builder = new StringBuilder(32);
    builder.append(quick ? "quick" : "lazy").append('.').append(color).append('.').append(animal);
    int id = RequestId.next();
    topicSender.send(builder.toString(), id);
    return SendResponse.of(id);
  }
}
