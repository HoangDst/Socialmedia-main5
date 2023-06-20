package com.network.socialmedia.data.response.homePage;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class CommentResponse {
    private String Desc;
    private int userId;
    private int postId;

}
