package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.asaunin.QueueConfiguration.DEFAULT_QUEUE;

@Component
public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	private static final String FIRST_LISTENER = "First";
	private static final String SECOND_LISTENER = "Second";

	@RabbitListener(id = FIRST_LISTENER, queues = DEFAULT_QUEUE)
	public void receiveFirstMessage(String message) {
		log.info("{} listener received a message '{}' from the '{}'", FIRST_LISTENER, message, DEFAULT_QUEUE);
	}

	@RabbitListener(id = SECOND_LISTENER, queues = DEFAULT_QUEUE)
	public void receiveSecondMessage(String message) {
		log.info("{} listener received a message '{}' from the '{}'", SECOND_LISTENER, message, DEFAULT_QUEUE);
	}

}