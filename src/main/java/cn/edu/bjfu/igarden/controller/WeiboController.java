package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.User;
import cn.edu.bjfu.igarden.service.UserService;
import org.apache.catalina.connector.Request;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by Cookie on 2019/9/10.
 */
@SuppressWarnings("deprecation")
@RestController
public class WeiboController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;
    @PostMapping(value = "/weiBo")
    public Object weiBo(@RequestParam("access_token") String access_token, @RequestParam("uid") String uid){
        User user = new User();
        String url = "https://api.weibo.com/2/users/show.json?access_token="+access_token+"&uid="+uid;
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpGet accessget = new HttpGet(URI.create(url));
        String password = new String();
        try {
            HttpResponse response = client.execute(accessget);
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader accessreader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                StringBuilder builder = new StringBuilder();
                for (String temp = accessreader.readLine(); temp != null; temp = accessreader.readLine()) {
                    builder.append(temp);
                }
                JSONObject accessobject = new JSONObject(builder.toString().trim());
                System.out.println(accessobject);
                //姓名
                user.setUsername(accessobject.getString("id"));
                //密码
                user.setPassword(accessobject.getString("id"));
                //头像
                user.setAvatar(accessobject.getString("avatar_hd"));
                //昵称
                user.setNickname(accessobject.getString("screen_name"));

                if (accessobject.getString("gender").equals("m")) {
                    user.setSex("男");
                } else {
                    user.setSex("女");
                }
                password = user.getPassword();

                if (userService.findByUsername(user.getUsername()) == null) {
                    userService.addUser(user);
                }

            }
           }catch(Exception e) {
                e.printStackTrace();
            }
        return (userController.weixinlogin(user.getUsername(), password));
    }
}
