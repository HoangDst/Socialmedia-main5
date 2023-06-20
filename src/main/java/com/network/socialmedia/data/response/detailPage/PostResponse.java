package com.network.socialmedia.data.response.detailPage;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PostResponse {
    private String title;
    private String Desc;

    private UserResponse userResponse;

    private List<CommentResponse> commentResponse;

    private List<String>PostRecommend;
    private Integer numComment;
    private String Topic;

}
