package com.example;

import com.hm.social.tables.pojos.User;



public interface IUserRepo {
    public User getUserById(Integer id);
    public User findUserByUserAccount(String userAccount);

    public boolean insertUser(User user);
    public User updateUser(User user, Integer id);
    public User deleteUser (Integer id);
}
