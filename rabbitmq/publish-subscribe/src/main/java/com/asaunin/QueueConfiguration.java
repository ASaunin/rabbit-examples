package com.asaunin;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

	static final String FIRST_QUEUE = "first.queue";
	static final String SECOND_QUEUE = "second.queue";
	static final String DEFAULT_EXCHANGE = "fanout.exchange";

	@Bean
	public FanoutExchange exchange(){
		return new FanoutExchange(DEFAULT_EXCHANGE);
	}

	@Bean
	public Queue firstQueue() {
		return new Queue(FIRST_QUEUE);
	}

	@Bean
	public Queue secondQueue() {
		return new Queue(SECOND_QUEUE);
	}

	@Bean
	public Binding firstBinding(){
		return BindingBuilder.bind(firstQueue()).to(exchange());
	}

	@Bean
	public Binding secondBinding(){
		return BindingBuilder.bind(secondQueue()).to(exchange());
	}

}
