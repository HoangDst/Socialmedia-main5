package com.network.socialmedia.repository.user;

import com.hm.social.tables.pojos.User;
import org.springframework.stereotype.Component;


public interface IUserRepository {
    User insert(User user);

    User getById(Integer userId);
}
