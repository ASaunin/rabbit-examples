package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.asaunin.QueueConfiguration.ORANGE_QUEUE;
import static com.asaunin.QueueConfiguration.LAZY_OR_RABBIT_QUEUE;

@Component
public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	@RabbitListener(queues = ORANGE_QUEUE)
	public void aliveListener(String message) {
		log.info("{} was routed to the 'Orange' pool", message);
	}

	@RabbitListener(queues = LAZY_OR_RABBIT_QUEUE)
	public void deadListener(String message) {
		log.info("{} was routed to the 'Lazy/Rabbit' pool", message);
	}

}