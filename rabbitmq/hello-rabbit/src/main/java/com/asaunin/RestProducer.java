package com.asaunin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.asaunin.QueueConfiguration.DEFAULT_QUEUE;

@RestController
public class RestProducer {

	private static final Logger log = LoggerFactory.getLogger(RestProducer.class);

	private static final String DEFAULT_MESSAGE = "Hello, REST Rabbit!";

	private final RabbitTemplate template;

	@Autowired
	public RestProducer(final RabbitTemplate template) {
		this.template = template;
	}

	@GetMapping(value = "/send") //Preferable to use post, but not so representative
	public ResponseEntity<String> send(
			@RequestParam(name = "msg", defaultValue = DEFAULT_MESSAGE, required = false) String message) {
		template.convertAndSend(DEFAULT_QUEUE, message);
		log.info("Sent rest message: {}", message);
		return ResponseEntity.ok(message);
	}

}
