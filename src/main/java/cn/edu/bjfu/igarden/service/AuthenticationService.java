package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class AuthenticationService{
    private static final long EXPIRE_TIME = 5*1000;
    public String getToken(User user) {
        // 过期时间，单位毫秒

    // 生成过期时间
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        String token = "";
//        Integer id=new Integer(user.getId());
        try {
            token = JWT.create()
//                    .withExpiresAt(date)
                    .withAudience(user.getId().toString())          // 将 user id 保存到 token 里面
                    .sign(Algorithm.HMAC256(user.getPassword()));   // 以 password 作为 token 的密钥
        } catch (Exception ignore) {

//    } catch (Exception ex) {
        // Handle other exceptions appropriately
    }
        return token;
    }
}
