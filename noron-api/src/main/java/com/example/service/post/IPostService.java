package com.example.service.post;

import com.example.data.response.PostResponse;

public interface IPostService {

    PostResponse getById(Integer postId);
}
