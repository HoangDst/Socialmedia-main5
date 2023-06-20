package com.example;

import com.hm.social.tables.pojos.*;

import java.util.List;


public interface IDetailPostPageRepo {
    public Post getPostById(Integer id);
    public List<Comment> getAllNewComments(Integer id);
    public List<Comment> getAllCommonComments(Integer id);
    public User getUserById(Integer id);
    public List<Post> getPostByTopic(Integer id);
    public Topic getTopicById(Integer id);
    public Comment getCommentById(Integer id);
    public Comment insertComment(Comment comment);
    public boolean updatePostCommentCount( Integer id);
    public Reply insertReply(Reply reply1);
    public Reply getReplyById(Integer id);
}
