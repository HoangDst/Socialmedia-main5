package com.network.socialmedia.repository.user;

import com.hm.social.Tables;
import com.hm.social.tables.pojos.User;
import com.hm.social.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository

public class UserRepositoryImpl implements IUserRepository{
    private final DSLContext dslContext;

    public UserRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public User insert(User user) {
        UserRecord userRecord = new UserRecord();
        userRecord.from(user);
        return dslContext.insertInto(Tables.POST)
                .set(userRecord)
                .returning()
                .fetchOne()
                .into(User.class);
    }


    @Override
    public User getById(Integer userId) {
        return null;
    }
}
