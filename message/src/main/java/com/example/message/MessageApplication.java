package com.example.message;

import brave.Tracing;
import brave.sampler.Sampler;
import brave.spring.rabbit.SpringRabbitTracing;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@EnableBinding(Source.class)
@IntegrationComponentScan

public class MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}




/*
args


	@Bean
	public SpringRabbitTracing springRabbitTracing(Tracing tracing) {
		return SpringRabbitTracing.newBuilder(tracing)
				.writeB3SingleFormat(false) // for more efficient propagation
				.remoteServiceName("my-mq-service")
				.build();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
										 SpringRabbitTracing springRabbitTracing) {
		RabbitTemplate rabbitTemplate = springRabbitTracing.newRabbitTemplate(connectionFactory);
		// other customizations as required
		return rabbitTemplate;
	}
*/

	@RestController
	class MessageServiceRestController {

		@Autowired
		Source source;

		@Autowired
		RabbitTemplate rabbitTemplate;

		@GetMapping("/hi")
		public String template() {


			Log log = LogFactory.getLog(getClass());


			log.info("this is the message sender");

			source.output().send(MessageBuilder.withPayload("hello world").build());

			return "Hello World";


		}

	}
}

