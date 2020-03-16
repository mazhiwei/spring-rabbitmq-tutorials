package org.mzw.rabbitmq.tutorials.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TutorialsController {
  @GetMapping("/sendDirectMessage")
  public String sendDirectMessage() {
    return "ok";
  }
}
