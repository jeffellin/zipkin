package com.example.hop3;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Hop3Application {

	public static void main(String[] args) {
		SpringApplication.run(Hop3Application.class, args);
	}


	@RestController
	class MessageServiceRestController {

		private Log log = LogFactory.getLog(getClass());


		@GetMapping("/hi")
		Map<String, String> message(HttpServletRequest httpRequest) {


			log.info("this is hop2");

			String key = "message";

			Map<String, String> response = new HashMap<>();

			String value = "Hi, from a REST endpoint: " + System.currentTimeMillis();

			response.put(key, value);


			System.out.println(response);


			return response;
		}
	}
}

