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
		System.out.println(loginService.loginByEmail("727958843@qq.com", "pan1xiao3"));
	}

}
