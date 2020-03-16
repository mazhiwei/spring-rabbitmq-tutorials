package org.mzw.rabbitmq.tutorials.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @GetMapping("/sendDirectMessage")
  public String sendDirectMessage() {
    return "ok";
  }
}
