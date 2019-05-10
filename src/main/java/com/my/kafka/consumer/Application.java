
package com.my.kafka.consumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class Application {

    private final Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private KafkaTemplate<Object, Object> template;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @KafkaListener(id = "myGroup", topics = "topic1")
    public void listen(List<MyClass> data) {
        logger.info("Received: " + data.size());
    }

    @PostConstruct
    public void PublishData(){
        for (int i=0; i<200; i++) {
            template.send("topic1", "Hello"+i);
        }
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("topic1", 1, (short) 1);
    }

}