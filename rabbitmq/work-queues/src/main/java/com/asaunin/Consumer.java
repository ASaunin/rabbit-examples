package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static com.asaunin.QueueConfiguration.DEFAULT_QUEUE;

public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	private String name; // TODO: 29.03.2017 Better to use listener id

	public Consumer(String name) {
		this.name = name;
	}

	@RabbitListener(queues = DEFAULT_QUEUE)
	public void receiveMessage(String message) {
		log.info("{} received message: {}", this.name, message);
	}

}
