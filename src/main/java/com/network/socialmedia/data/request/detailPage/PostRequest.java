package com.network.socialmedia.data.request.detailPage;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostRequest {
    private String title;
    private String desc;
    private String img;
    private int userid;
}
