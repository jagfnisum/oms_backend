package oms.backend.oms_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.core.KafkaTemplate;

// @SpringBootApplication
@SpringBootApplication(scanBasePackages="oms.*")
@EnableJpaRepositories(basePackages="oms.*")
//@EnableJpaRepositories(basePackages="oms.backend.oms_backend")
@EntityScan(basePackages="oms.*")
public class OmsbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmsbackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate <String, String> kafkaTemplate){
		return args -> {
			kafkaTemplate.send("oms-create-order", "sent to create order");
			kafkaTemplate.send("oms-cancel-order", "sent to cancel order");
		};
	}
}
