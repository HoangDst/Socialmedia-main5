package com.network.socialmedia.data.request.detailPage;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

public class UserRequest {
    private int userId;
    private String userName;
    private String email;
    private String name;
    private String urlPic;

}
