package com.example.seigmovies.service;

import com.example.seigmovies.entity.Role;
import com.example.seigmovies.entity.User;
import com.example.seigmovies.mapper.RoleRepository;
import com.example.seigmovies.mapper.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    public User getUser(String email) {
        User user = null;
        Object object = redisTemplate.opsForValue().get("user_" + email);
        if (object != null) {
            user = (User) object;
        } else {
            user = userRepository.findByEmail(email);
            if (user != null) {
                redisTemplate.opsForValue().set("user_" + email, user);
            }
        }
        return user;
    }

    // 根据账号查询用户权限（角色）
    public List<Role> getUserAuthorities(String email) {
        List<Role> authorities = null;
        Object object = redisTemplate.opsForValue().get("authorities_" + email);
        if (object != null) {
            authorities = (List<Role>) object;
        } else {
            List<Role> authoritiesByEmail = roleRepository.findAuthoritiesByAccount(email);
            if (authoritiesByEmail != null) {
                redisTemplate.opsForValue().set("authorities_" + email, authoritiesByEmail);
            }
        }
        return authorities;
    }
}
