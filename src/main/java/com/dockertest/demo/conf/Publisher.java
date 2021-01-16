package com.dockertest.demo.conf;

import com.dockertest.demo.model.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public @Service
class Publisher implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  private final Reader reader;

  public Publisher(RabbitTemplate rabbitTemplate, Reader reader) {
    this.rabbitTemplate = rabbitTemplate;
    this.reader = reader;
  }

  public @Override void run(String... args) throws Exception {
    rabbitTemplate
        .convertAndSend(RabbitMQConfiguration.TOPIC_EXCHANGE_NAME, "user.new", getUserModel());
    reader.getLatch().await(10, TimeUnit.SECONDS);
  }

  private List<UserModel> getUserModel() {
    List<UserModel> list = new ArrayList<>();
    list.add(new UserModel(1L,"Jerry"));
    list.add(new UserModel(2L,"Tom"));
    return list;
  }
}