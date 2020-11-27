package com.ffk.springBootDemo;

import com.meituan.service.inf.kms.client.Kms;
import com.meituan.service.inf.kms.utils.KmsResultNullException;
import com.sankuai.inf.kms.pangolin.api.model.EncryptDataInfo;
import com.sankuai.inf.kms.pangolin.api.model.PangolinSecurityException;
import com.sankuai.inf.kms.pangolin.api.service.encode.EncryptDataCodecFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class KmsWebTest extends SpringBootDemoApplicationTests{

    private static final String ALGORITHM_HMAC_SHA1 = "HmacSHA1";

    public static String getSignature(byte[] data, byte[] key)
            throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec signingKey = new SecretKeySpec(key, ALGORITHM_HMAC_SHA1);
        Mac mac = Mac.getInstance(ALGORITHM_HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data);
        return new String(Base64.encodeBase64(rawHmac));
    }

    private String getSign(String method, String uri, String date) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String string_to_sign = method + " " + uri + "\n" + date;
        return "MWS s3plus:" + getSignature(string_to_sign.getBytes("UTF-8"), "9ebf2cfb853f7a724ab910ee64ed23a8".getBytes("UTF-8"));
    }

    @Test
    public void postTest() throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        String datetime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String url = "http://kms.test.sankuai.com/manage/api/platformV2/getNotifyListByName"; //拼下url
        HttpPost request = new HttpPost(url);
        request.addHeader("Date", datetime);
        request.addHeader("Authorization", getSign(HttpMethod.POST.name(), "/manage/api/platformV2/addKey", datetime));
        CloseableHttpResponse response = HttpClients.createDefault().execute(request);
        String msg = EntityUtils.toString(response.getEntity());
        System.out.println(msg);
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void getTest() throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        String datetime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String url = "http://10.24.158.23:8080/manage/api/platformV2/getNameStoreInfo"; //拼下url
        url += "?name=1&env=dev";
        HttpGet request = new HttpGet(url);
        request.addHeader("Date", datetime);
        request.addHeader("Authorization", getSign(HttpMethod.GET.name(), "/manage/api/platformV2/getNameStoreInfo", datetime));
        CloseableHttpResponse response = HttpClients.createDefault().execute(request);
        String msg = EntityUtils.toString(response.getEntity());
        System.out.println(msg);
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void baUserSecret() {
        System.out.println(RandomStringUtils.randomAlphanumeric(32));
    }

    @Test
    public void key_apply_id() throws PangolinSecurityException {
        byte[] bytes = Base64.decodeBase64("AgAAAAEAAAElAAAAPGccG07Qdmx/iMZBlBfL42GgulIqhGqFsnHZs7HwWCG20uhwT5hCN40HaRLr75RRFoPzBAuKXosuXy/Wfw==");
        EncryptDataInfo encryptDataInfo = EncryptDataCodecFactory.queryByData(bytes).decode(bytes);
        System.out.println(encryptDataInfo.getMkSerialNumber());
    }

    @Test
    public void kmsGetKey() throws KmsResultNullException {
        String key = Kms.getByName("takeaway-eventbusiness-service", "auth_client_mapi-appkit-service");
        System.out.println(key);

    }
}
