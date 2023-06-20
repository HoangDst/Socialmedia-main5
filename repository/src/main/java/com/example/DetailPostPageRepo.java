package com.example;


import com.hm.social.tables.pojos.*;
import com.hm.social.tables.records.CommentRecord;
import com.hm.social.tables.records.ReplyRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.jooq.impl.DSL.rand;

@Repository
public class DetailPostPageRepo implements IDetailPostPageRepo {
    private final DSLContext dslContext;

    public DetailPostPageRepo(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    private com.hm.social.tables.Post posts = com.hm.social.tables.Post.POST;
    private com.hm.social.tables.Comment comments = com.hm.social.tables.Comment.COMMENT;
    private com.hm.social.tables.User users = com.hm.social.tables.User.USER;
    private com.hm.social.tables.Reply reply = com.hm.social.tables.Reply.REPLY;

    public Post getPostById(Integer id) {
        Post post = dslContext.select()
                .from(posts)
                .where(posts.ID.eq(id))
                .fetchOneInto(Post.class);
        return post;
    }

    @Override
    public List<Comment> getAllNewComments(Integer id) {
        List<Comment> allCommentSortedByDate = dslContext.select()
                .from(comments)
                .where(comments.POST_ID.eq(id))
                .orderBy(comments.CREATED_AT)
                .fetchInto(Comment.class);
        return allCommentSortedByDate;
    }

    @Override
    public List<Comment> getAllCommonComments(Integer id) {
        List<Comment> allCommentSortedByDate = dslContext.select()
                .from(comments)
                .where(comments.POST_ID.eq(id))
                .orderBy(comments.NUM_VOTE)
                .fetchInto(Comment.class);
        return allCommentSortedByDate;
    }

    @Override
    public User getUserById(Integer id) {
        User user = dslContext.select()
                .from(users)
                .where(users.ID.eq(id))
                .fetchOneInto(User.class);
        return user;
    }


    @Override
    public List<Post> getPostByTopic(Integer id) {
        return dslContext.selectFrom(posts)
                .where(posts.TOPIC_ID.eq(id))
                .orderBy(rand())
                .limit(2)
                .offset(0)
                .fetchInto(Post.class);
    }


    @Override
    public Topic getTopicById(Integer id) {
        return dslContext.select()
                .from(com.hm.social.tables.Topic.TOPIC)
                .where(com.hm.social.tables.Topic.TOPIC.ID.eq(id))
                .fetchOneInto(Topic.class);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return dslContext.select()
                .from(comments)
                .where(comments.ID.eq(id))
                .fetchOneInto(Comment.class);
    }

    @Override
    public Comment insertComment(Comment comment) {
        Post post = getPostById(comment.getPostId());
        CommentRecord commentsRecord = dslContext.insertInto(comments, comments.CONTENT, comments.CREATED_AT
                        , comments.POST_ID, comments.COMMENTUSER_ID,
                        comments.POSTUSER_ID, comments.NUM_VOTE
                        , comments.NUM_REPLY
                )
                .values(comment.getContent(), LocalDateTime.now(), comment.getPostId(),
                        comment.getCommentuserId(), comment.getPostuserId(), 0, 0)
                .returning(comments.ID)
                .fetchOne();


        int id = commentsRecord.getValue(comments.ID);
        return getCommentById(id);
    }

    @Override
    public boolean updatePostCommentCount(Integer id) {
        Post posts1 = getPostById(id);
        return dslContext.update(posts)
                .set(posts.NUM_COMMENT, posts1.getNumComment() + 1)
                .where(posts.ID.eq(id))
                .execute() == 1;

    }
    @Override
    public Reply insertReply(Reply reply1) {
        Post post = getPostById(reply1.getPostId());
        ReplyRecord replyRecord = dslContext.insertInto(reply, reply.CONTENT,reply.COMMENT_ID,
                        reply.POST_ID,reply.CREATED_AT,reply.VOTECOUNT,reply.USEROWNER_ID)
                .values(reply1.getContent(), reply1.getCommentId()
                        ,reply1.getPostId(), LocalDateTime.now(), reply1.getVotecount()
                        ,reply1.getUserownerId() )
                .returning(reply.ID)
                .fetchOne();

        int id = replyRecord.getValue(reply.ID);
        return getReplyById(id);
    }
    @Override
    public Reply getReplyById(Integer id) {
        return dslContext.select()
                .from(reply)
                .where(reply.ID.eq(id))
                .fetchOneInto(Reply.class);
    }

}
