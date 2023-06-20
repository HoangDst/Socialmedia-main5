package com.example.data.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostRequest {
    private Integer userOwnerId;
    private String title;
    private String content;
    private Integer topicId;
}
