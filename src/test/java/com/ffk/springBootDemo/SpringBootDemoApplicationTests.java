package com.ffk.springBootDemo;

import com.sankuai.inf.kms.pangolin.api.model.EncryptionRequest;
import com.sankuai.inf.kms.pangolin.api.service.EncryptServiceFactory;
import com.sankuai.inf.kms.pangolin.api.service.IEncryptService;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.GeneralSecurityException;

@SpringBootTest
class SpringBootDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void biToHex() {
		System.out.println(RandomStringUtils.randomAlphanumeric(32));
		String str = "0.5.3-BATCH-SNAPSHOT";
		str = str.replaceAll("[^\\d.]", "");
		System.out.println(str);
	}

	@Test
	public void test() throws GeneralSecurityException {
		EncryptionRequest request = EncryptionRequest.Builder.anEncryptionRequest()
				.withNamespace("com.meituan.mtrace.test.MtraceTestA")	// 命名空间
				.withKeyName("TestForHmacDigest")			// 密钥名
				.build();
		IEncryptService encryptService = EncryptServiceFactory.create(request);
		System.out.println(encryptService.encryptUTF8String("hello"));
		EncryptionRequest request1 = EncryptionRequest.Builder.anEncryptionRequest()
				.withNamespace("com.meituan.mtrace.test.MtraceTestA")	// 命名空间
				.withKeyName("testEleven")			// 密钥名
				.build();
		IEncryptService encryptService1 = EncryptServiceFactory.create(request);
		System.out.println(encryptService1.encryptUTF8String("hello"));
	}
}
