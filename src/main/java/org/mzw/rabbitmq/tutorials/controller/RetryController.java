package org.mzw.rabbitmq.tutorials.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.mzw.rabbitmq.tutorials.sender.RetryPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryController {
  @Autowired
  private RetryPublisher publisher;

  @GetMapping("/retry-send")
  public void send() throws IOException, TimeoutException {
    publisher.send();
  }
}