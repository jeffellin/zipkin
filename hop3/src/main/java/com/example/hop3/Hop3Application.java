package com.example.hop3;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@SpringBootApplication
public class Hop3Application {

	public static void main(String[] args) {
		SpringApplication.run(Hop3Application.class, args);
	}


	@RestController
	class MessageServiceRestController {

		private Log log = LogFactory.getLog(getClass());


		@GetMapping("/hop3")
		Map<String, String> message(HttpServletRequest httpRequest) {

			Collections.list(httpRequest.getHeaderNames()).stream().forEach(h->log.info(h+"="+httpRequest.getHeader(h)));

			log.info("this is the third hop");

			String key = "message";

			Map<String, String> response = new HashMap<>();

			String value = "Hi, from a REST endpoint: " + System.currentTimeMillis();

			response.put(key, value);


				//String nullStr = null;;
			//nullStr.equals("foo");
			return response;
		}
	}
}

