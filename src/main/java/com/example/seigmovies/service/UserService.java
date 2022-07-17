package com.example.seigmovies.service;

import com.example.seigmovies.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    public int addUser(User user);

    public User selectUser(@Param("username") String username, @Param("password") String password);

}
