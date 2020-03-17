package org.mzw.rabbitmq.tutorials.task;

import org.mzw.rabbitmq.tutorials.ResponseMessage;
import org.mzw.rabbitmq.tutorials.models.TaskResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
  @GetMapping("/task")
  public TaskResponse fetch(@RequestParam int id) {
    String content = ResponseMessage.getAndRemove(id);
    TaskResponse response = new TaskResponse();
    if (content != null) {
      response.setOk(true);
      response.setContent(content);
    }
    return response;
  }
}
