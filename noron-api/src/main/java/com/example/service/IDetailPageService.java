package com.example.service;

import com.example.data.request.ShortCommentRequest;
import com.example.data.response.PostResponse;
import com.example.data.response.ShortCommentResponse;

import java.util.List;

public interface IDetailPageService {
    public PostResponse getDetailPostById(Integer Id);
    public List<ShortCommentResponse> getAllShortCommentResponseByCommon(Integer postId);
    public List<ShortCommentResponse> getAllShortCommentResponseByDate(Integer postId);

    public List<String> getRecommendPost( String topicId);
    public ShortCommentResponse insertComment(ShortCommentRequest shortCommentResquest, Integer userOwnerId);


}
