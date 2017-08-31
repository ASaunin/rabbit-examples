package com.asaunin;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

	static final String DEFAULT_QUEUE = "rpc.queue";
	static final String DEFAULT_EXCHANGE = "rpc.exchange";
	static final String DEFAULT_KEY = "rpc";

	@Bean
	public DirectExchange exchange(){
		return new DirectExchange(DEFAULT_EXCHANGE);
	}

	@Bean
	public Queue queue() {
		return new Queue(DEFAULT_QUEUE);
	}

	@Bean
	public Binding binding(){
		return BindingBuilder.bind(queue()).to(exchange()).with(DEFAULT_KEY);
	}

}
