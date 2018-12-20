package com.example.hop1;

import brave.Tracing;
import brave.sampler.Sampler;
import brave.spring.rabbit.SpringRabbitTracing;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.net.WriteBuffer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@IntegrationComponentScan
@SpringBootApplication
//@EnableBinding(Sink.class)
@EnableScheduling
public class Hop1Application {

	public static void main(String[] args) {
		SpringApplication.run(Hop1Application.class, args);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}



	@Component
	class MessageProcessor {

		@Autowired
		RestTemplate restTemplate;

		private Log log = LogFactory.getLog(getClass());

		//@ServiceActivator(inputChannel = Sink.INPUT)
		@Scheduled(fixedDelay = 5000)
		public void onMessage() {
			log.info("this is the 1st hop");

			String url = "http://localhost:8082/hop2";

			ParameterizedTypeReference<Map<String, String>> ptr =
					new ParameterizedTypeReference<Map<String, String>>() {
					};

			ResponseEntity<Map<String, String>> responseEntity =
					this.restTemplate.exchange(url, HttpMethod.GET, null, ptr);

			log.info("this is the 1st hop");

		}
	}
}

