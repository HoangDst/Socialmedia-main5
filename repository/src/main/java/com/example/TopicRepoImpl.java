package com.example;

import com.hm.social.tables.pojos.Topic;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class TopicRepoImpl implements ITopicRepo{
    private final DSLContext dslContext;

    public TopicRepoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Topic getTopicById(Integer id) {
        return dslContext.selectOne()
                .from(com.hm.social.tables.Topic.TOPIC)
                .where(com.hm.social.tables.Topic.TOPIC.ID.eq(id))
                .fetchOneInto(Topic.class);
    }
}
