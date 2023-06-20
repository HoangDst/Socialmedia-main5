package com.network.socialmedia.data.response.detailPage;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class CommentResponse {
    private String Desc;
    private int userId;
    private int postId;
}
