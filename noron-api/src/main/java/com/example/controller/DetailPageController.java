package com.example.controller;

import com.example.config.model.DetailPage;
import com.example.data.request.PostRequest;
import com.example.data.request.ShortCommentRequest;
import com.example.data.response.PostResponse;
import com.example.data.response.ShortCommentResponse;
import com.example.logger.IncreaseView;
import com.example.service.IDetailPageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/detailPage")
@RestController
public class DetailPageController {
    private IDetailPageService detailPageService;

    private KafkaTemplate<String, String> kafkaTemplate;

    public DetailPageController(IDetailPageService detailPageService, KafkaTemplate<String, String> kafkaTemplate) {
        this.detailPageService = detailPageService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication.getName());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<DetailPage> DetailPageByCommon(@PathVariable Integer postId) {
        PostResponse postResponse = detailPageService.getDetailPostById(postId);
        List<ShortCommentResponse> commentResponseList = detailPageService.getAllShortCommentResponseByCommon(postId);
        List<String> recommendedPost = detailPageService.getRecommendPost(postResponse.getTopicId());
        DetailPage detailPage = new DetailPage().setPostResponse(postResponse)
                .setRecommendPost(recommendedPost)
                .setShortCommentResponseList(commentResponseList);


        return ResponseEntity.ok(detailPage);


    }

    @GetMapping("/{postId}/newest")
    public ResponseEntity<DetailPage> DetailPageByDate(@PathVariable("postId") Integer postId) {
        PostResponse postResponse = detailPageService.getDetailPostById(postId);
        List<ShortCommentResponse> commentResponseList = detailPageService.getAllShortCommentResponseByDate(postId);
        List<String> recommendedPost = detailPageService.getRecommendPost(postResponse.getTopicId());
        DetailPage detailPage = new DetailPage().setPostResponse(postResponse)
                .setRecommendPost(recommendedPost)
                .setShortCommentResponseList(commentResponseList);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(detailPage);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ShortCommentResponse> insertComment(@PathVariable Integer userOwnerId, @RequestBody ShortCommentRequest shortCommentRequest) {
        ShortCommentResponse shortCommentResponse = detailPageService.insertComment(shortCommentRequest, userOwnerId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(shortCommentResponse);
    }

    @PostMapping("/newPost")
    public ResponseEntity<PostResponse> insertPost(@RequestBody PostRequest postRequest) {
        PostResponse postResponse = detailPageService.insertPost(postRequest);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/increasingView/{postId}")
    public String increaseView(@PathVariable Integer postId) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        IncreaseView increaseView = new IncreaseView()
                .setPostId(postId)
                .setUserAccount("test");
        ObjectMapper objectMapper = new ObjectMapper();
        String Json = objectMapper.writeValueAsString(increaseView);
        kafkaTemplate.send("test1", "user", Json);
        return "oke";
    }
}
