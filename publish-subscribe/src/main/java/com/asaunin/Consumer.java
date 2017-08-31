package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.asaunin.QueueConfiguration.FIRST_QUEUE;
import static com.asaunin.QueueConfiguration.SECOND_QUEUE;

@Component
public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	private static final String FIRST_LISTENER = "First";
	private static final String SECOND_LISTENER = "Second";

	@RabbitListener(id = FIRST_LISTENER, queues = FIRST_QUEUE)
	public void receiveFirstMessage(String message) {
		log.info("{} listener received a message '{}' from the '{}'", FIRST_LISTENER, message, FIRST_QUEUE);
	}

	@RabbitListener(id = SECOND_LISTENER, queues = SECOND_QUEUE)
	public void receiveSecondMessage(String message) {
		log.info("{} listener received a message '{}' from the '{}'", SECOND_LISTENER, message, SECOND_QUEUE);
	}

}