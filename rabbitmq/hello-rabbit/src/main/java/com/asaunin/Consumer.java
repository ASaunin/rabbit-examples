package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.asaunin.QueueConfiguration.DEFAULT_QUEUE;

@Service
public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	@RabbitListener(queues = DEFAULT_QUEUE)
	public void receiveMessage(String message) {
		log.info("Received message: {}", message);
	}

}
