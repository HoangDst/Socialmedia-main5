package com.network.socialmedia.data.response.homePage;

import com.network.socialmedia.data.response.detailPage.CommentResponse;
import com.network.socialmedia.data.response.detailPage.UserResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PostResponse {
    private String title;
    private String Desc;

    private UserResponse userResponse;

    private List<CommentResponse> commmentResponse;

    private List<String>PostRecommend;
    private Integer numComment;
    private String Topic;
}
