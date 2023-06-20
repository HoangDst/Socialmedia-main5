package com.example.data.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@Accessors(chain=true)
public class UserResponse {
    private String userName;
    private String userAccount;
    private String userAvatar;
    private String userEmail;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updateAt;
}
