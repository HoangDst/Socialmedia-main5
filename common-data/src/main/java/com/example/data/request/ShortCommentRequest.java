package com.example.data.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class ShortCommentRequest {
private Integer userOwnerId;
private String content;
private Integer postId;

}
