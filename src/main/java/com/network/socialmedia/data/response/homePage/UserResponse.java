package com.network.socialmedia.data.response.homePage;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponse {
    private String userName;
    private String userAvatar;
}
