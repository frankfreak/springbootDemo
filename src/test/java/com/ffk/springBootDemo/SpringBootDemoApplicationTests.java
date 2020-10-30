package com.ffk.springBootDemo;

import com.ffk.springBootDemo.dao.UserDao;
import com.ffk.springBootDemo.service.KmsService;
import com.ffk.springBootDemo.service.LoginService;
import com.sankuai.inf.kms.pangolin.api.model.AppType;
import com.sankuai.inf.kms.pangolin.api.util.KmsPangolinConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.GeneralSecurityException;

@SpringBootTest
class SpringBootDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	KmsService kmsService;

	@Test
	public void endeCrypt() throws GeneralSecurityException {
		KmsPangolinConfig.getInstance().setMac(true);
		KmsPangolinConfig.getInstance().setOnline(false);
		KmsPangolinConfig.getInstance().setAppType(AppType.CLOUD);
		System.out.println(kmsService.encrypt("hello"));
	}

}
