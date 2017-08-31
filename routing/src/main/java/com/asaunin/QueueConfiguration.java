package com.asaunin;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class QueueConfiguration {

	static final String ALIVE_QUEUE = "alive.queue";
	static final String DEAD_QUEUE = "dead.queue";
	static final String DEFAULT_EXCHANGE = "direct.exchange";

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(DEFAULT_EXCHANGE);
	}

	@Bean
	public Queue aliveQueue() {
		return new Queue(ALIVE_QUEUE);
	}

	@Bean
	List<Binding> aliveBinding() {
		return Arrays.stream(Entities.values())
				.filter(Entities::isAlive)
				.map(e -> BindingBuilder.bind(
						aliveQueue())
						.to(exchange())
						.with(e))
				.collect(Collectors.toList());
	}

	@Bean
	public Queue deadQueue() {
		return new Queue(DEAD_QUEUE);
	}

	@Bean
	List<Binding> deadBinding() {
		return Arrays.stream(Entities.values())
				.filter(Entities::isDead)
				.map(e -> BindingBuilder.bind(
						deadQueue())
						.to(exchange())
						.with(e))
				.collect(Collectors.toList());
	}

	enum Entities {
		HUMAN(Boolean.TRUE),
		TREE(Boolean.TRUE),
		STONE(Boolean.FALSE),
		WATER(Boolean.FALSE),
		ZOMBIE(null),
		SIMPLE_CAT(Boolean.TRUE),
		SHREDINGERS_CAT(null);

		private Boolean state = null;

		Entities(Boolean state) {
			this.state = state;
		}

		public Boolean isAlive() {
			return !(this.state == Boolean.FALSE);
		}

		public Boolean isDead() {
			return !(this.state == Boolean.TRUE);
		}

		public static Entities getByIndex(int index) {
			return values()[index];
		}

	}

}
