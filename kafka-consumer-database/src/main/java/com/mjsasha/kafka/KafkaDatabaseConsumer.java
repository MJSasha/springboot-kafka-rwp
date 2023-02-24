package com.mjsasha.kafka;

import com.mjsasha.entity.WikimediaData;
import com.mjsasha.repository.WikimediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaDatabaseConsumer {

    private final WikimediaDataRepository wikimediaDataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(topics = "wikimedia_resent_change", groupId = "wikimediaGroup")
    public void consume(String eventMessage){
        log.info(String.format("### Event message received -> %s", eventMessage));

        var wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);

        wikimediaDataRepository.save(wikimediaData);
    }
}
