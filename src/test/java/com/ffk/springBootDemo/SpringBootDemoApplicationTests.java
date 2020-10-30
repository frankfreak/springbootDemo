package com.ffk.springBootDemo;

import com.ffk.springBootDemo.dao.UserDao;
import com.ffk.springBootDemo.domain.User;
import com.ffk.springBootDemo.service.KmsService;
import com.ffk.springBootDemo.service.LoginService;
import com.meituan.service.inf.kms.utils.KmsResultNullException;
import com.sankuai.inf.kms.pangolin.api.cat.Cat;
import com.sankuai.inf.kms.pangolin.api.cat.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.GeneralSecurityException;

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
		Cat.logEvent("", "");
		Transaction transaction = Cat.newTransaction("", "");
		transaction.setStatus("");
		transaction.complete();
	}

	@Autowired
	KmsService kmsService;
	@Test
	public void getName() throws KmsResultNullException {
		System.out.println(kmsService.getName());
	}

	@Test
	public void endeCrypt() throws GeneralSecurityException {
		System.out.println(kmsService.encrypt("hello"));
	}

}
