package com.example.hop2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Hop2Application {

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Hop2Application.class, args);

	}
	@RestController
	class MessageServiceRestController {
		private Log log = LogFactory.getLog(getClass());



		@Autowired
		RestTemplate restTemplate;


		@GetMapping("/hi")
		ResponseEntity<Map<String, String>> template() {

			log.info("this is 2");

			String url = "http://localhost:8083/hi";

			ParameterizedTypeReference<Map<String, String>> ptr =
					new ParameterizedTypeReference<Map<String, String>>() {
					};

			ResponseEntity<Map<String, String>> responseEntity =
					this.restTemplate.exchange(url, HttpMethod.GET, null, ptr);

			return ResponseEntity
					.ok()
					.contentType(responseEntity.getHeaders().getContentType())
					.body(responseEntity.getBody());


		}
	}
}

