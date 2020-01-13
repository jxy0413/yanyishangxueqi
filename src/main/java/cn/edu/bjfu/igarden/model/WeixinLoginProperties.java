package cn.edu.bjfu.igarden.model;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="weixinconfig")
public class WeixinLoginProperties {

    private String weixinappID; // 商户appid

    private String weixinappSecret; // 私钥 pkcs8格式的

    private String weixinappID1;

    private String weixinappSecret1;

    public String getWeixinappID1() {
        return weixinappID1;
    }

    public void setWeixinappID1(String weixinappID1) {
        this.weixinappID1 = weixinappID1;
    }

    public String getWeixinappSecret1() {
        return weixinappSecret1;
    }

    public void setWeixinappSecret1(String weixinappSecret1) {
        this.weixinappSecret1 = weixinappSecret1;
    }

    public String getWeixinappID() {
        return weixinappID;
    }

    public void setWeixinappID(String weixinappID) {
        this.weixinappID = weixinappID;
    }

    public String getWeixinappSecret() {
        return weixinappSecret;
    }

    public void setWeixinappSecret(String weixinappSecret) {
        this.weixinappSecret = weixinappSecret;
    }

}
