package cn.edu.bjfu.igarden.common;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Signer {
    public static String hmacSha1(String value,
                                  String key) {
        try {
            byte[] keyBytes = key.getBytes("utf-8"); //密钥先做UTF-8编码
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac =
                    mac.doFinal(value.getBytes("utf-8")); //待签数据先做 UTF-8编码
            return
                    Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception var6) {
            throw new RuntimeException(var6);
        }
    }
}