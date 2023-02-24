package com.mjsasha;

import com.mjsasha.handler.WikimediaChangesHandler;
import com.mjsasha.kafka.WikimediaKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProducerApplication implements CommandLineRunner {
    private final WikimediaKafkaProducer wikimediaKafkaProducer;

    public SpringBootProducerApplication(WikimediaKafkaProducer wikimediaKafkaProducer) {
        this.wikimediaKafkaProducer = wikimediaKafkaProducer;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProducerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        wikimediaKafkaProducer.sendMessage();
    }
}