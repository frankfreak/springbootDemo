package com.ffk.springBootDemo.dao;


import com.ffk.springBootDemo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;


@Mapper
public interface UserDao {

    @Select("select * from User")
    List<User> getAll();

    @Select("select * from User where userId=#{userId}")
    User getById(Integer userId);

    @Select("select * from User where email=#{email}")
    User getByEmail(String email);

    @Select("select * from User where userName=#{useName}")
    User getByUserName(String userName);

    @Insert({"insert into User(userId, userName, password, email, homeTown)", "values(#{userId}, #{userName}, #{password}, #{email}, #{homeTown})"})
    int insertUser(User user);
}
