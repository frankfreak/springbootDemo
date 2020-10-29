package com.ffk.springBootDemo.service;


import com.meituan.service.inf.kms.client.Kms;
import com.meituan.service.inf.kms.utils.KmsResultNullException;
import org.springframework.stereotype.Service;

@Service
public class KmsService {

    public String getName() throws KmsResultNullException {
        String name = Kms.getByName("com.meituan.mtrace.test.MtraceTestA", "keyForUniTestWithType");
        return name;
    }
}
