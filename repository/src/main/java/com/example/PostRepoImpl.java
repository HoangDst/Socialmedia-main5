package com.example;

import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.records.PostRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.hm.social.Tables.POST;
import static org.jooq.impl.DSL.rand;

@Repository
public class PostRepoImpl implements IPostRepo {
    private DSLContext dslContext;

    public PostRepoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    private com.hm.social.tables.Post posts = com.hm.social.tables.Post.POST;

    @Override
    public Post getPostById(Integer id) {
        return dslContext.select()
                .from(posts)
                .where(posts.ID.eq(id))
                .fetchOneInto(Post.class);
    }

    @Override
    public List<Post> getAllPostsByCommon() {
        return dslContext.select()
                .from(posts)

                .fetchInto(Post.class);
    }

    @Override
    public List<Post> getAllPostByDate() {

        return dslContext.select()
                .from(posts)
                .orderBy(posts.CREATED_AT)
                .fetchInto(Post.class);
    }

    @Override
    public List<Post> getPostByTopic(Integer id) {
        return dslContext.select()
                .from(posts)
                .where(posts.TOPIC_ID.eq(id))
                .fetchInto(Post.class);

    }

    @Override
    public Post insertPost(Post post) {
        PostRecord postRecord = dslContext.insertInto(posts, posts.CONTENT, posts.TITLE, posts.CREATED_AT
                        , posts.USER_ID, posts.TOPIC_ID
                        , posts.NUM_COMMENT)
                .values(post.getContent(), post.getTitle(), LocalDateTime.now(),
                        post.getUserId(), 1, 0)
                .returning(posts.ID)
                .fetchOne();


        int id = postRecord.getValue(posts.ID);
        return getPostById(id);
    }

    @Override
    public Post updatePost(Post post, Integer id) {
        dslContext.update(posts)
                .set(posts.UPDATED_AT, LocalDateTime.now())
                .set(posts.TITLE, post.getTitle())
                .set(posts.CONTENT, post.getContent())
                .set(posts.TOPIC_ID, post.getTopicId())
                .where(posts.ID.eq(id))
                .execute();
        return getPostById(id);
    }

    @Override
    public Post deletePost(Integer id) {
        dslContext.update(posts)
                .set(posts.DELETED_AT, LocalDateTime.now())
                .where(posts.ID.eq(id))
                .execute();
        return getPostById(id);
    }

    @Override
    public List<Post> getRandPostByTopic(Integer topicId) {
        return dslContext.select(rand())
                .from(posts)
                .where(posts.TOPIC_ID.eq(topicId))
                .limit(10)
                .fetchInto(Post.class);
    }

    @Override
    public void increasenumView(Integer postId) {
        int numView = getPostById(postId).getNumView();
        dslContext.update(posts)
                .set((posts.NUM_VIEW), numView + 1)
                .where(posts.ID.eq(postId))
                .execute();

    }

    @Override
    public void increasenumView(Integer postId, Long numView) {
        dslContext.update(POST)
                .set(POST.NUM_VIEW, POST.NUM_VIEW.plus(numView))
                .where(POST.ID.eq(postId))
                .execute();
    }
}
