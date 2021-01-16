package com.dockertest.demo;

import com.dockertest.demo.conf.RabbitMQConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


    private final RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    public @Override void run(String... args) throws Exception {
        rabbitTemplate
                .convertAndSend(RabbitMQConfiguration.TOPIC_EXCHANGE_NAME, "user.new", getUserModel());
        reader.getLatch().await(10, TimeUnit.SECONDS);
    }


}
