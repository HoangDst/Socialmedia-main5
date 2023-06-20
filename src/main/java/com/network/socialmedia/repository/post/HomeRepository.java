package com.network.socialmedia.repository.post;

import com.hm.social.tables.Comment;
import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.pojos.User;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HomeRepository {
    private final DSLContext dslContext;

    public HomeRepository (DSLContext dslContext){
        this.dslContext = dslContext;
    }

    private com.hm.social.tables.Post post = com.hm.social.tables.Post.POST;
    private com.hm.social.tables.Comment comment = com.hm.social.tables.Comment.COMMENT;

    private com.hm.social.tables.User users = com.hm.social.tables.User.USER;
    private com.hm.social.tables.Reply reply = com.hm.social.tables.Reply.REPLY;

    public List<Post> getAllNewPost(){
        List<Post> allPostSortedByDate = dslContext.selectFrom(post)
                .orderBy(post.CREATED_AT.desc())
                .fetchInto(Post.class);
        return allPostSortedByDate;
    }

    public List<Post> getAllCommonPost(){
        List<Post> allPostSortedByDate = dslContext.selectFrom(post)
                .orderBy(post.NUM_COMMENT)
                .fetchInto(Post.class);
        return allPostSortedByDate;
    }



    public List<Comment> getAllComments(Integer id){
        List<Comment> allCommentSortedByDate = dslContext.select()
                .from(comment)
                .where(comment.POST_ID.eq(id))
                .orderBy(comment.CREATED_AT)
                .fetchInto(Comment.class);
        return allCommentSortedByDate;
    }
    public Comment getcommonComment(Integer id) {
        Comment commonComment = dslContext.select()
                .from(comment)
                .where(comment.POST_ID.eq(id))
                .orderBy(comment.NUM_VOTE)
                .limit(1)
                .offset(0)
                .fetchOneInto(Comment.class);
        return commonComment;
    }
    public User getUser (Integer id) {
        User user= dslContext.select()
                .from(users)
                .where(users.ID.eq(id))
                .fetchOneInto(User.class);
        return user;
    }
    public Integer numreply(Integer id){
        return dslContext.fetchCount(dslContext.selectFrom(reply)
                .where(reply.COMMENT_ID.eq(id)));
    }
}
