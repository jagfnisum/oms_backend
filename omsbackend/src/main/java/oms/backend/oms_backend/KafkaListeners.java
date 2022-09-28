package oms.backend.oms_backend;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = {"oms-create-order", "oms-cancel-order"},
            groupId = "groupId")
    void listener(String data) {
        System.out.println("Listener received: " + data);
    }
}