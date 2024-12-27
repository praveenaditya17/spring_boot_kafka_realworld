package net.javaguides.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

public class WikimediaChangesHandler implements EventHandler{

	private static final Logger LOGGER= LoggerFactory.getLogger(WikimediaChangesHandler.class);
	private KafkaTemplate<String, String> kafkaTempate;
	private String topic;

	public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTempate, String topic) {
		this.kafkaTempate = kafkaTempate;
		this.topic = topic;
	}

	@Override
	public void onOpen() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClosed() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {

		LOGGER.info(String.format("even data %s", messageEvent.getData()));
		kafkaTempate.send(topic, messageEvent.getData());
		
	}

	@Override
	public void onComment(String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
