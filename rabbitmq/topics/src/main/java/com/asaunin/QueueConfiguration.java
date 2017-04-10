package com.asaunin;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class QueueConfiguration {

	static final String ORANGE_QUEUE = "orange.queue";
	static final String LAZY_OR_RABBIT_QUEUE = "lazy.or.rabbit.queue";

	static final String DEFAULT_EXCHANGE = "topic.exchange";

	private static final String ORANGE_KEY = "*.orange.*";
	private static final String LAZY_KEY = "lazy.#";
	private static final String RABBIT_KEY = "*.*.rabbit";

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(DEFAULT_EXCHANGE);
	}

	@Bean
	public Queue orangeQueue() {
		return new Queue(ORANGE_QUEUE);
	}

	@Bean
	Binding orangeBinding() {
		return BindingBuilder.bind(orangeQueue()).to(exchange()).with(ORANGE_KEY);
	}

	@Bean
	public Queue lazyOrRabbitQueue() {
		return new Queue(LAZY_OR_RABBIT_QUEUE);
	}

	@Bean
	List<Binding> lazyOrRabbitBinding() {
		return Stream.of(LAZY_KEY, RABBIT_KEY)
				.map(e -> BindingBuilder.bind(
						lazyOrRabbitQueue())
						.to(exchange())
						.with(e))
				.collect(Collectors.toList());
	}

}
