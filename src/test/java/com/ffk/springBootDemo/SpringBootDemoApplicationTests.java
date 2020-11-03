package com.ffk.springBootDemo;

import com.ffk.springBootDemo.service.KmsService;
import com.sankuai.inf.kms.pangolin.api.model.AppType;
import com.sankuai.inf.kms.pangolin.api.service.HttpRemoteStrategy;
import com.sankuai.inf.kms.pangolin.api.util.KmsPangolinConfig;
import org.apache.commons.codec.Charsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootTest
class SpringBootDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	KmsService kmsService;

	static String rootCert = "/Users/panxiaoyu04/Downloads/demopki/root/root.jks";
	static String hostCert = "/Users/panxiaoyu04/Downloads/demopki/host/1234567/host.jks";

	@Test
	public void endeCrypt() throws GeneralSecurityException, IOException {
		KmsPangolinConfig.getInstance().setMac(true);
		KmsPangolinConfig.getInstance().setOnline(false);
		KmsPangolinConfig.getInstance().setAppType(AppType.CLOUD);
		KmsPangolinConfig.getInstance().setRootCertPath(rootCert);
		KmsPangolinConfig.getInstance().setHostCertPath(hostCert);
		KmsPangolinConfig.getInstance().setRootPassword("changeit");
		KmsPangolinConfig.getInstance().setHostPassword("changeit");
		HttpRemoteStrategy httpRemoteStrategy = HttpRemoteStrategy.instance;
		byte[] response = httpRemoteStrategy.doGet("/monitor/alive");
		String content = new String(response, Charsets.UTF_8);
		System.out.println(content);
		System.out.println(kmsService.encrypt("hello"));
	}

}
