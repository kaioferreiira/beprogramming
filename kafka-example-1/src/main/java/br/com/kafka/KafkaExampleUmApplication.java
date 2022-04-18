package br.com.kafka;

import com.sun.jdi.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@SpringBootApplication
public class KafkaExampleUmApplication {

	private final Logger logger = LoggerFactory.getLogger(KafkaExampleUmApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaExampleUmApplication.class, args);
	}


	@Bean
	public RecordMessageConverter converter() {
		return new JsonMessageConverter();
	}

	@KafkaListener(topics = "topic1", groupId = "topic1")
	public void listen(Foo2 foo) {
		logger.info("Received: " + foo);
		if (foo.getFoo().startsWith("fail")) {
//			throw new RuntimeException("failed");
			throw new InternalException("failed");
		}

		System.out.println("Hit Enter to terminate...");
	}

	@KafkaListener(id = "dltGroup", topics = "topic1.DLT")
	public void dltListen(String in) {

		logger.info("Received from DLT: " + in);
		System.out.println("DLT Hit Enter to terminate...");

	}


}
