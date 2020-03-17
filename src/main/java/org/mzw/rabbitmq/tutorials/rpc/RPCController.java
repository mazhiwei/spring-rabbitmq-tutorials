package org.mzw.rabbitmq.tutorials.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RPCController {
  @Autowired
  private RPCSender rpcSender;

  @GetMapping("/rpc")
  public void send() {
    rpcSender.send();
  }
}
