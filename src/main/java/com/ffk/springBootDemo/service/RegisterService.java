package com.ffk.springBootDemo.service;


import com.ffk.springBootDemo.dao.UserDao;
import com.ffk.springBootDemo.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegisterService {
    Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    UserDao userDao;

    public boolean checkUserName(String userName){
        User user = userDao.getByUserName(userName);
        return null == user;
    }

    public boolean checkUserEmail(String email){
        User user = userDao.getByEmail(email);
        return null == user;
    }

    public  boolean registerUser(User user){
        if(null == user){
            return false;
        }
        int u = userDao.insertUser(user);
        logger.info("insert user result: " + u);
        return true;
    }
}
