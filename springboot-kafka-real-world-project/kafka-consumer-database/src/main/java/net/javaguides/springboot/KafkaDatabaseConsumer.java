package net.javaguides.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.entity.WikimediaData;
import net.javaguides.springboot.repository.WikimediaDataRepository;

@Service
public class KafkaDatabaseConsumer {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	private WikimediaDataRepository dataRepository;
	
	
	public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}


	@KafkaListener(topics="wikimedia_recentchange",groupId="myGroup")
	public void consume(String eventMessage) {
		
		LOGGER.info(String.format("Evemt Message Received -> %s", eventMessage));
		
		WikimediaData wikimedia = new WikimediaData();
		wikimedia.setWikiEventData(eventMessage);
		
		dataRepository.save(wikimedia);
	}
}
