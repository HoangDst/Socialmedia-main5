package com.example.service;

import com.example.data.response.PostResponse;

import java.util.List;

public interface IHomePageService {
public List<PostResponse>getAllPostByCommon();
public List<PostResponse>getAllPostByNewest();
}
