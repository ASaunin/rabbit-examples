package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.asaunin.QueueConfiguration.DEFAULT_QUEUE;

@Component
public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	@RabbitListener(queues = DEFAULT_QUEUE)
	public int fibonacci(int request) {
		log.info("Server received the request: '{}'", request);

		final int response = fib(request);
		log.info("Server returned the response: '{}'", response);
		return response;
	}

	private static int fib(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		return fib(n-1) + fib(n-2);
	}

}