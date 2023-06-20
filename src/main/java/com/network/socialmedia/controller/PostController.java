package com.network.socialmedia.controller;

import com.network.socialmedia.data.request.detailPage.PostRequest;
import com.network.socialmedia.data.response.detailPage.PostResponse;
import com.network.socialmedia.service.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final IPostService postService;
    public PostController(IPostService postService){
        this.postService=postService;
    }
    @PostMapping("/add")
    public ResponseEntity<PostResponse>add(@RequestBody PostRequest request){
        PostResponse postResponse = postService.insert(request);
        return  ResponseEntity.ok(postResponse);
    }
    @PutMapping ("/update")
    public ResponseEntity<PostResponse>update(@RequestBody PostRequest request, @RequestBody Integer id){
        PostResponse postResponse = postService.update(request,id);
        return ResponseEntity.ok(postResponse);
        }
    @GetMapping("/get")
    public ResponseEntity<PostResponse>get(@RequestBody PostRequest request,@RequestBody Integer id){
        PostResponse postResponse=postService.get(request,id);
        return ResponseEntity.ok(postResponse);
    }
    }

