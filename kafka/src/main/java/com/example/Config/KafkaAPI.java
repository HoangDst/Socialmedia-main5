package com.example.Config;

import com.example.data.ViewIncreaseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaAPI {
    private final KafkaAPI kafkaProducer;

    public KafkaAPI(KafkaAPI kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @PostMapping("/view-increase")
    public ResponseEntity<String> increaseView(@RequestBody ViewIncreaseRequest request){
        //kafkaProducer.ViewIncreaseMessage(request);
        return ResponseEntity.ok("View increased successfully.");
    }



}
