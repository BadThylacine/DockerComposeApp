package com.dockertest.demo.conf;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public @Service
class Reader {

  private CountDownLatch latch = new CountDownLatch(1);

  @RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME, containerFactory = "listenerContainerFactory")
  public void receiveMessage(final List<Book> books) {
    books.forEach(System.out::println);
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }
}