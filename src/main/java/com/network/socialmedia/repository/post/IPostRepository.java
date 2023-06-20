package com.network.socialmedia.repository.post;

import com.hm.social.tables.pojos.Post;
import com.network.socialmedia.data.response.detailPage.PostResponse;
import org.springframework.stereotype.Component;


public interface IPostRepository {
    Post insert(Post post);
    Post update(Post post,Integer postId);
    PostResponse getById(Integer postId);
    PostResponse getByPage  (Integer page,Integer limit);
}
