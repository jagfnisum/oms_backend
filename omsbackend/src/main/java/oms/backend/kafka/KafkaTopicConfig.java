package oms.backend.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic omsCreateOrder() {
		return TopicBuilder.name("oms-order-create")
				.build();
	}

	@Bean
	public NewTopic omsCancelOrder() {
		return TopicBuilder.name("oms-order-canceled")
				.build();
	}

    @Bean
    public NewTopic wmsShipOrder() {
        return TopicBuilder.name("wms-order-shipped")
				.build();
    }
}
