package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

import static com.asaunin.QueueConfiguration.*;
import static com.asaunin.QueueConfiguration.DEFAULT_EXCHANGE;

@Service
public class Producer {

	private static final Logger log = LoggerFactory.getLogger(Producer.class);

	private final RabbitTemplate template;

	@Autowired
	public Producer(final RabbitTemplate template) {
		this.template = template;
	}

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		final int index = ThreadLocalRandom.current().nextInt(0, Entities.values().length);
		final String entity = Entities.getByIndex(index).toString();

		template.setExchange(DEFAULT_EXCHANGE);
		template.setRoutingKey(entity);
		template.convertAndSend(entity);
		log.info("Here comes '{}'", entity);
	}

}
