package com.ffk.springBootDemo.service;


import com.google.common.base.Charsets;
import com.sankuai.inf.kms.pangolin.api.model.EncryptionRequest;
import com.sankuai.inf.kms.pangolin.api.service.EncryptServiceFactory;
import com.sankuai.inf.kms.pangolin.api.service.IEncryptService;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

@Service
public class KmsService {

    public String encrypt(String text) throws GeneralSecurityException {
        EncryptionRequest request = EncryptionRequest.Builder.anEncryptionRequest()
                .withNamespace("com.sankuai.kms.pangolin.cloud")	// 命名空间
                .withKeyName("test-middle")			// 密钥名
                .build();
        // IEncryptService 是线程安全的，创建一次即可
        IEncryptService encryptService = EncryptServiceFactory.create(request);

        byte[] plain = text.getBytes(Charsets.UTF_8);
        byte[] cipher = encryptService.encrypt(plain); 											// 加密 byte[]，输出的 byte[] 不能直接 new String(xx) 后存储
        byte[] decrypt = encryptService.decrypt(cipher);

        String cipherText = encryptService.encryptUTF8String(text);		// 加密字符串，该字符串必须是 UTF-8 编码的，否则请自行解码后使用 encrypt(byte[]) 方法加密
        String decryptText = encryptService.decryptUTF8String(cipherText);	// 解密字符串
        return decryptText;
    }
}
