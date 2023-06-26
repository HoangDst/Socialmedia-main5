package com.example;

import com.hm.social.tables.pojos.Post;
import org.springframework.context.annotation.Configuration;

import java.util.List;


public interface IPostRepo {
    public Post getPostById(Integer id);
    public List<Post> getAllPostsByCommon();
    public List<Post> getAllPostByDate();

    public List<Post> getPostByTopic(Integer id);
    public Post insertPost(Post post);
    public Post updatePost(Post post, Integer id);
    public Post deletePost (Integer id);
    public List<Post> getRandPostByTopic(Integer topicId);
    public void increasenumView(Integer postId);


    void increasenumView(Integer postId, Long numView);
}