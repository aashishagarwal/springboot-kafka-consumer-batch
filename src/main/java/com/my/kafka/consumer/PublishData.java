package com.my.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishData {

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @GetMapping(path = "/publish/data/{path}")
    public void publishData(@PathVariable String path) {
        this.template.send("topic1", new MyClass(path));
    }

}
