package com.network.socialmedia.controller;

import com.network.socialmedia.data.request.detailPage.PostRequest;
import com.network.socialmedia.data.response.detailPage.PostResponse;
import com.network.socialmedia.service.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/homepage")
public class HomeController {
    private final IPostService postService;
    public HomeController(IPostService postService){
        this.postService=postService;
    }


    @GetMapping("/newest")
    public ResponseEntity<PostResponse>getAllNewPost(PostRequest request){
        PostResponse postResponse =postService.insert(request);
        return ResponseEntity.ok(postResponse);
    }
    @GetMapping("/common")
    public ResponseEntity<PostResponse>getAllCommonPost(PostRequest request){
        PostResponse postResponse=postService.insert(request);
        return ResponseEntity.ok(postResponse);
    }
}
