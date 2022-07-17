package com.example.seigmovies.service.impl;

import com.example.seigmovies.entity.User;
import com.example.seigmovies.mapper.UserMapper;
import com.example.seigmovies.mapper.UserRepository;
import com.example.seigmovies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User selectUser(String username, String password) {
        return userMapper.selectUser(username,password);
    }
}
