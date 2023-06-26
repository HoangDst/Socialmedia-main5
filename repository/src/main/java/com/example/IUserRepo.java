package com.example;

import com.hm.social.tables.pojos.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration

public interface IUserRepo {
    public User getUserById(Integer id);
    public User findUserByUserAccount(String userAccount);

    List<User> getUserByIds(List<Integer> ids);

    public boolean insertUser(User user);
    public User updateUser(User user, Integer id);
    public User deleteUser (Integer id);
}
