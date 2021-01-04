package com.ffk.springBootDemo;

import com.dianping.cat.Cat;
import com.ffk.springBootDemo.domain.MWSUser;
import com.ffk.springBootDemo.utils.JsonUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class PangolinTest extends SpringBootDemoApplicationTests{
    private static final String ALGORITHM_HMAC_SHA1 = "HmacSHA1";
    // stg
//    private static final String mwsClientSecret = "0439d4c9d45298aaea1a18b702bb6f62";
    // test
    private static final String mwsClientSecret = "client_secret";
    private static final String userBase64 = "PORTAL-PROXY-USER";
    private static final String sign = "PORTAL-PROXY-SIGN";
    private static final String date = "PORTAL-PROXY-DATE";

    private static final String DOMAIN = "http://localhost:8080";
//    private static final String DOMAIN ="http://kms.inf.st.sankuai.com";

    public static String getSignature(String encryptStr) {
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKey secretKey = new SecretKeySpec(mwsClientSecret.getBytes(), "HmacSHA1");
            mac.init(secretKey);

            java.util.Base64.Encoder base64Encoder = Base64.getEncoder();
            return base64Encoder.encodeToString(mac.doFinal(encryptStr.getBytes()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to sign encryptStr: " + encryptStr, e);
        }
    }

    @Test
    public void test1() throws IOException {
        String datetime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
        MWSUser user = new MWSUser();
        user.setLogin("panxiaoyu04");
        user.setName("潘晓宇");
        String userBase64 = JsonUtil.toJsonStr(user);
        Base64.Encoder base64Encoder = Base64.getEncoder();
        String userInfoString = new String(base64Encoder.encode(userBase64.getBytes()));
        String uri = "/pangolin/keyManage/applyKey";
        String url = DOMAIN + uri +
                "?origin=com.meituan.mtrace.test.MtraceTestA" +
                "&keyId=TestForSM4" +
                "&type=SM4_CBC" +
                "&level=MIDDLE" +
                "&desc=SM4密钥测试" +
                "&updateInterval=168" +
                "&extensionFlag=0";
        HttpPost request = new HttpPost(url);
        String encryptStr = request.getMethod()
                + " " + uri
                + " " + datetime
                + " " + userInfoString;
        System.out.println(encryptStr);
        String sign = getSignature(encryptStr);
        request.addHeader(date, datetime);
        request.addHeader(PangolinTest.userBase64, userInfoString);
        request.addHeader(PangolinTest.sign, sign);
        CloseableHttpResponse response = HttpClients.createDefault().execute(request);
        String msg = EntityUtils.toString(response.getEntity());
        System.out.println(msg);
        System.out.println(response.getStatusLine().getStatusCode());
    }
}
