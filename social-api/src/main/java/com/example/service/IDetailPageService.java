package com.example.service;

import com.example.data.request.PostRequest;
import com.example.data.request.ShortCommentRequest;
import com.example.data.response.PostResponse;
import com.example.data.response.ShortCommentResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IDetailPageService {
    public PostResponse getDetailPostById(Integer Id);
    public List<ShortCommentResponse> getAllShortCommentResponseByCommon(Integer postId);
    public List<ShortCommentResponse> getAllShortCommentResponseByDate(Integer postId);

    public List<String> getRecommendPost( Integer topicId);
    public ShortCommentResponse insertComment(ShortCommentRequest shortCommentResquest, Integer userOwnerId);
    public PostResponse insertPost(PostRequest postRequest);

}
