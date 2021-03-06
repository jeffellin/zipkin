package com.example.message;

import brave.Tracing;
import brave.sampler.Sampler;
import brave.spring.rabbit.SpringRabbitTracing;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@EnableBinding(Source.class)
@IntegrationComponentScan
@EnableScheduling
@Slf4j
public class MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}


	@Component
	class MessageServiceRestTimer {

		@Autowired
		Source source;

		@Autowired
		RabbitTemplate rabbitTemplate;

		//@GetMapping("/hi")
		@Scheduled(fixedDelay = 5000)
		public String template() {


			//Log log = LogFactory.getLog(getClass());


			log.info("this is the message sender");

			source.output().send(MessageBuilder.withPayload("hello world").build());

			log.info("this is the message sender");


			return "Hello World";


		}

	}



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

