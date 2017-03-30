package com.asaunin;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

	static final String DEFAULT_QUEUE = "rabbit.queue";

	@Bean
	public Queue hello() {
		return new Queue(DEFAULT_QUEUE);
	}

}
