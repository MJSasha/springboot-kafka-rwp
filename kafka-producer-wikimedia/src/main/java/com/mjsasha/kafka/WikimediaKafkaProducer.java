package com.mjsasha.kafka;

import com.launchdarkly.eventsource.EventSource;
import com.mjsasha.handler.WikimediaChangesHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WikimediaKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_resent_change";

        var changesHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        var url = "https://stream.wikimedia.org/v2/stream/recentchange";
        var builder = new EventSource.Builder(changesHandler, URI.create(url));
        var eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
