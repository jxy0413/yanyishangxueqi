package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.User;
import cn.edu.bjfu.igarden.model.WeixinLoginProperties;
import cn.edu.bjfu.igarden.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * 第三方微信登录
 *
 * @author Administrator
 */
@SuppressWarnings("deprecation")
@RestController
public class WeXinController {


    //微信公众平台申请
    //应用唯一标识，在微信开放平台提交应用审核通过后获得 appID
    //应用密钥AppSecret，在微信开放平台提交应用审核通过后获得 appSecret
    //TpAccesstoken 用来保存微信返回的用户信息oppid等


    @Resource
    private WeixinLoginProperties weixinLoginProperties;

    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/weChat")
    public Object weChat(String code) throws Exception {
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        String password = new String();
        String appID = weixinLoginProperties.getWeixinappID();
        String appSecret = weixinLoginProperties.getWeixinappSecret();
        String accesstoken;
        String openid = null;
        int expiresIn;
        String unionid;
        if (code != null) {
            System.out.println(code);
        }
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appID + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        URI uri = URI.create(url);
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(uri);
        HttpResponse response;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                    sb.append(temp);
                }
                JSONObject object = new JSONObject(sb.toString().trim());//去空格
                System.out.println("object:" + object);
                accesstoken = object.getString("access_token");
                System.out.println("accesstoken:" + accesstoken);
                openid = object.getString("openid");
                System.out.println("openid:" + openid);
//                expiresIn = (int) object.getLong("expires_in");
//                unionid = object.getString("unionid");
                String accessurl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accesstoken + "&openid=" + openid;
                org.apache.http.client.HttpClient accessclient = new DefaultHttpClient();
                HttpGet accessget = new HttpGet(URI.create(accessurl));
                try {
                    HttpResponse accessresponse = client.execute(accessget);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        BufferedReader accessreader = new BufferedReader(
                                new InputStreamReader(accessresponse.getEntity().getContent(), "UTF-8"));
                        StringBuilder builder = new StringBuilder();
                        for (String temp = accessreader.readLine(); temp != null; temp = accessreader.readLine()) {
                            builder.append(temp);
                        }
                        JSONObject accessobject = new JSONObject(builder.toString().trim());
                        System.out.println(accessobject);
                        user.setUsername(accessobject.getString("unionid"));
                        user.setPassword(accessobject.getString("unionid"));
                        System.out.println(user.getPassword());
                        user.setAvatar(accessobject.getString("headimgurl"));
                        user.setNickname(accessobject.getString("nickname"));
                        if (accessobject.getString("sex").equals("1")) {
                            user.setSex("男");
                        } else {
                            user.setSex("女");
                        }
                        password = user.getPassword();
                        if (userService.findByUsername(user.getUsername()) == null) {
                            userService.addUser(user);
                        }

                        // String passwordHash =  userService.passwordToHash(user.getPassword());
                        // System.out.println(passwordHash);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (userController.weixinlogin(user.getUsername(), password));
    }


    @PostMapping(value = "/weChatPlant")
    public Object weChatPlant(String code) throws Exception {
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        String password = new String();
        String appID = weixinLoginProperties.getWeixinappID1();
        String appSecret = weixinLoginProperties.getWeixinappSecret1();
        System.out.println(appID);
        System.out.println(appSecret);
        String accesstoken;
        String openid = null;
        int expiresIn;
        String unionid;
        if (code != null) {
            System.out.println(code);
        }
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appID + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        URI uri = URI.create(url);
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(uri);
        HttpResponse response;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                    sb.append(temp);
                }
                JSONObject object = new JSONObject(sb.toString().trim());//去空格
                System.out.println("object:" + object);
                accesstoken = object.getString("access_token");
                System.out.println("accesstoken:" + accesstoken);
                openid = object.getString("openid");
                System.out.println("openid:" + openid);
//                expiresIn = (int) object.getLong("expires_in");
//                unionid = object.getString("unionid");
                String accessurl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accesstoken + "&openid=" + openid;
                org.apache.http.client.HttpClient accessclient = new DefaultHttpClient();
                HttpGet accessget = new HttpGet(URI.create(accessurl));
                try {
                    HttpResponse accessresponse = client.execute(accessget);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        BufferedReader accessreader = new BufferedReader(
                                new InputStreamReader(accessresponse.getEntity().getContent(), "UTF-8"));
                        StringBuilder builder = new StringBuilder();
                        for (String temp = accessreader.readLine(); temp != null; temp = accessreader.readLine()) {
                            builder.append(temp);
                        }
                        JSONObject accessobject = new JSONObject(builder.toString().trim());
                        System.out.println(accessobject);
                        user.setUsername(accessobject.getString("unionid"));
                        user.setPassword(accessobject.getString("unionid"));
                        System.out.println(user.getPassword());
                        user.setAvatar(accessobject.getString("headimgurl"));
                        user.setNickname(accessobject.getString("nickname"));
                        if (accessobject.getString("sex").equals("1")) {
                            user.setSex("男");
                        } else {
                            user.setSex("女");
                        }
                        password = user.getPassword();
                        if (userService.findByUsername(user.getUsername()) == null) {
                            userService.addUser(user);
                        }

                        // String passwordHash =  userService.passwordToHash(user.getPassword());
                        // System.out.println(passwordHash);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (userController.weixinlogin(user.getUsername(), password));
    }
}
