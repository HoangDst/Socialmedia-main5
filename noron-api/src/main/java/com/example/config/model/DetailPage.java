package com.example.config.model;

import com.example.data.response.PostResponse;
import com.example.data.response.ShortCommentResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class DetailPage {
    private PostResponse postResponse;
    private List<ShortCommentResponse> shortCommentResponseList;
    private List<String>recommendPost;
}
