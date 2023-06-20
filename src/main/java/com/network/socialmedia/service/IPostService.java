package com.network.socialmedia.service;

import com.network.socialmedia.data.request.detailPage.PostRequest;
import com.network.socialmedia.data.response.detailPage.PostResponse;
import org.springframework.stereotype.Component;

public interface IPostService {
    PostResponse insert (PostRequest request);

    PostResponse update (PostRequest request, Integer postId);

    PostResponse get(PostRequest request,Integer id);
}
