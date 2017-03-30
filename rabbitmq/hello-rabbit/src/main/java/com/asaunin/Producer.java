package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.asaunin.QueueConfiguration.DEFAULT_QUEUE;

@Service
public class Producer {

	private static final Logger log = LoggerFactory.getLogger(Producer.class);

	private static final String DEFAULT_MESSAGE = "Hello, Rabbit!";

	private final RabbitTemplate template;

	@Autowired
	public Producer(final RabbitTemplate template) {
		this.template = template;
	}

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		template.convertAndSend(DEFAULT_QUEUE, DEFAULT_MESSAGE);
		log.info("Sent a scheduled message '{}'", DEFAULT_MESSAGE);
	}

}
