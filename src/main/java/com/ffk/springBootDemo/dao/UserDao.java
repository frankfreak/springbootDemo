package com.ffk.springBootDemo.dao;


import com.ffk.springBootDemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;


@Mapper
public interface UserDao {

    @Select("select * from user")
    List<User> getAll();

    @Select("select * from user where userId=#{userId}")
    User getById(Integer userId);

    @Select("select * from user where email=#{email}")
    User getByEmail(String email);
}
