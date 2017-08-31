package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.asaunin.QueueConfiguration.DEAD_QUEUE;
import static com.asaunin.QueueConfiguration.ALIVE_QUEUE;

@Component
public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	@RabbitListener(queues = ALIVE_QUEUE)
	public void aliveListener(String message) {
		log.info("{} was routed to the alive pool", message);
	}

	@RabbitListener(queues = DEAD_QUEUE)
	public void deadListener(String message) {
		log.info("{} was routed to the dead pool", message);
	}

}