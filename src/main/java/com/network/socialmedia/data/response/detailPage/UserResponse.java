package com.network.socialmedia.data.response.detailPage;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponse {
    private String userName;
    private String userAvatar;
}
