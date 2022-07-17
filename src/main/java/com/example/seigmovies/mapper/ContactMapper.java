package com.example.seigmovies.mapper;

import com.example.seigmovies.entity.Contact;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactMapper {

    @Insert("insert into tb_contact (userId,email,password,content,upload,publishTime) " +
            "values(#{userId},#{email},#{password},#{content},#{upload},#{publishTime})")
    public int addContact(Contact contact);
}
