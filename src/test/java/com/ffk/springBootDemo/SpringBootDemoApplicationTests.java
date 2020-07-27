package com.ffk.springBootDemo;

import com.ffk.springBootDemo.dao.UserDao;
import com.ffk.springBootDemo.domain.User;
import com.ffk.springBootDemo.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootDemoApplicationTests {

	@Autowired
	UserDao userDao;

	@Autowired
	LoginService loginService;
	@Test
	void contextLoads() {
	}
	@Test
	void daoTest(){
		User user = new User();
		user.setEmail("1111@gmail.com");
		user.setUserId(67839);
		user.setPassword("zhu4ling2");
		user.setUserName("zhuly");
		userDao.insertUser(user);
		System.out.println(userDao.getById(67839).toString());
	}

}
