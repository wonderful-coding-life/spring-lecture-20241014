package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		var product1 = Product.builder().name("갤럭시").price(10000).description("삼성이 만들었어요").build();
		var product2 = Product.builder().name("갤럭시").price(10000).description("애플이 만들었어요").build();

		if (product1 != null) {
			// trace < debug < info < warn < error
			if (product1.equals(product2)) {
				log.info("same");
			} else {
				log.warn("different");
			}
		} else {
			log.error("null object");
		}

		log.trace("trace level log");
		log.debug("debug level log");
		log.info("info level log");
		log.warn("warn level log");
		log.error("error level log");



	}
}
