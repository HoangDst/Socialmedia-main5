package com.network.socialmedia.repository.post;

import com.hm.social.Tables;
import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.records.PostRecord;
import com.network.socialmedia.data.response.detailPage.PostResponse;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository

public class PostRepositoryImpl implements IPostRepository {
    private final DSLContext dslContext;

        public PostRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    @Override
    public Post insert (Post post){
        PostRecord postRecord = new PostRecord();
        postRecord.from(post);
        return dslContext.insertInto(Tables.POST)
                .set(postRecord)
                .returning()
                .fetchOne()
                .into(Post.class);
    }

    @Override
    public Post update(Post post, Integer postId) {
        return null;
    }

    @Override
    public PostResponse getById(Integer postId) {
        return null;
    }

    @Override
    public PostResponse getByPage(Integer page, Integer limit) {
        return null;
    }


}
