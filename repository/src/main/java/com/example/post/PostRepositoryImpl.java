package com.example.post;

import com.example.BaseMysqlRepository;
import com.hm.social.Tables;
import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.records.PostRecord;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl extends BaseMysqlRepository<PostRecord, Post, Integer>
        implements IPostRepository {
    @Override
    public TableImpl<PostRecord> getTable() {
        return Tables.POST;
    }
}
