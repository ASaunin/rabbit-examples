package com.asaunin;

import com.asaunin.model.Animal;
import com.asaunin.model.Color;
import com.asaunin.model.State;
import com.asaunin.utils.RandomEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

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
		final String state = RandomEnum.get(State.class).name().toLowerCase();
		final String color = RandomEnum.get(Color.class).name().toLowerCase();
		final String animal = RandomEnum.get(Animal.class).name().toLowerCase();

		final String entity = new StringJoiner(".")
				.add(state)
				.add(color)
				.add(animal)
				.toString();

		template.setExchange(DEFAULT_EXCHANGE);
		template.setRoutingKey(entity);
		template.convertAndSend(entity);
		log.info("Here comes '{}'", entity);
	}

}
