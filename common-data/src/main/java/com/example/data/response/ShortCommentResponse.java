package com.example.data.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ShortCommentResponse {
    private String content;
    private UserResponse userResponse;
    private Integer numVote;
    private Integer numComment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
