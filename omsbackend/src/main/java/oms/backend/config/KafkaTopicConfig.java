package oms.backend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic omsCreateOrder() {
		return TopicBuilder.name("oms-create-order")
				.build();
	}

	@Bean
	public NewTopic omsCancelOrder() {
		return TopicBuilder.name("oms-cancel-order")
				.build();
	}
}
