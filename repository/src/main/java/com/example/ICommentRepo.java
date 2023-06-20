package com.example;

import com.hm.social.tables.pojos.Comment;

import java.util.List;


public interface ICommentRepo {
    public Comment getCommentById(Integer id);

    public List<Comment> getAllCommentsByCommon(Integer postId);

    public List<Comment> getAllCommentByDate(Integer postid);


    public Comment updateComment(Comment comment, Integer id);

    public Comment deleteComment(Integer id);

    public Comment insertComment(Comment comment);
}
