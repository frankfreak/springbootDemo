package com.ffk.springBootDemo.service;


import com.ffk.springBootDemo.contoller.LoginController;
import com.ffk.springBootDemo.dao.UserDao;
import com.ffk.springBootDemo.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    UserDao userDao;

    public boolean loginById(Integer id, String password){
        if(password == null || password.length() == 0) return false;
        User user = userDao.getById(id);
        if(null == user) return false;

        return user.getPassword().equals(password);
    }
    public boolean loginByEmail(String email, String password){
        if(password == null || password.length() == 0) return false;
        if(email == null || email.length() == 0) return false;
        User user = userDao.getByEmail(email);

        if(null == user) return false;

        return user.getPassword().equals(password);
    }
}
