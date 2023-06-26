package com.example.service;

import com.example.IPostRepo;
import com.example.logger.IncreaseView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@EnableKafka
public class IncreasingView {
    private final IPostRepo postRepo;

    public IncreasingView(IPostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @KafkaListener(topics = "test1", groupId = "user",
            containerFactory = "customKafkaListenerContainerFactory")
    public void increasingsView(List<String> messages) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Integer, Long> postCountMap = messages.stream()
                .map(s -> getIncreaseView(objectMapper, s))
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(IncreaseView::getPostId, Collectors.counting()));
        postCountMap.forEach(postRepo::increasenumView);
    }

    private static IncreaseView getIncreaseView(ObjectMapper objectMapper, String s) {
        try {
            return objectMapper.readValue(s, IncreaseView.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
