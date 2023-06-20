package com.example.controller;

import com.example.controller.model.DetailPage;
import com.example.data.request.ShortCommentRequest;
import com.example.data.response.PostResponse;
import com.example.data.response.ShortCommentResponse;
import com.example.service.IDetailPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController("/homepage/{post}")
public class DetailPageController {
    private IDetailPageService detailPageService;

    public DetailPageController(IDetailPageService detailPageService) {
        this.detailPageService = detailPageService;
    }
    @GetMapping("/{postId}/")
    public ResponseEntity<DetailPage> DetailPageByCommon(@PathVariable Integer postId){
        PostResponse postResponse = detailPageService.getDetailPostById(postId);
        List<ShortCommentResponse> commentResponseList = detailPageService.getAllShortCommentResponseByCommon(postId);
        List<String> recommendedPost = detailPageService.getRecommendPost(postResponse.getTopic());
        DetailPage detailPage = new DetailPage().setPostResponse(postResponse)
                .setRecommendPost(recommendedPost)
                .setShortCommentResponseList(commentResponseList);
        return ResponseEntity.ok(detailPage);
    }
    @GetMapping("/{postId}/newest")
    public ResponseEntity<DetailPage> DetailPageByDate(@PathVariable("postId") Integer postId, @PathVariable("topicId")Integer topicId){
        PostResponse postResponse = detailPageService.getDetailPostById(postId);
        List<ShortCommentResponse> commentResponseList = detailPageService.getAllShortCommentResponseByDate(postId);
        List<String> recommendedPost = detailPageService.getRecommendPost(postResponse.getTopic());
        DetailPage detailPage = new DetailPage().setPostResponse(postResponse)
                .setRecommendPost(recommendedPost)
                .setShortCommentResponseList(commentResponseList);
        return ResponseEntity.ok(detailPage);
    }
    @PostMapping("/{userId}")
    public ResponseEntity<ShortCommentResponse> insertComment(@PathVariable Integer userOwnerId, @RequestBody ShortCommentRequest shortCommentRequest){
        ShortCommentResponse shortCommentResponse= detailPageService.insertComment(shortCommentRequest,userOwnerId);
        return ResponseEntity.ok(shortCommentResponse);
    }
}
