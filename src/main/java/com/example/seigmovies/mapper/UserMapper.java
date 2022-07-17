package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into tb_user (userId,account,password,roleId,avatar) values(#{userId},#{account},#{password},#{roleId},#{avatar})")
    public int addUser(User user);

    @Select("select * from tb_user where account=#{username} and password = #{password}")
    public User selectUser(@Param("username") String username, @Param("password") String password);

}
