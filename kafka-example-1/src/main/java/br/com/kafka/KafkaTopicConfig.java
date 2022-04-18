package br.com.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic topic() {
//		TopicBuilder.name("topic1-teste").build()
		return new NewTopic("topic1.teste", 1, (short) 1);
	}

	@Bean
	public NewTopic dlt() {
		return new NewTopic("topic1.teste.DLT", 1, (short) 1);
	}

}
