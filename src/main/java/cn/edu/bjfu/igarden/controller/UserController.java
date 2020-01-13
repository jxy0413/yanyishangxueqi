package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.Experts;
import cn.edu.bjfu.igarden.entity.Invite;
import cn.edu.bjfu.igarden.entity.Login;
import cn.edu.bjfu.igarden.entity.User;
import cn.edu.bjfu.igarden.service.AuthenticationService;
import cn.edu.bjfu.igarden.service.UserService;
import cn.edu.bjfu.igarden.util.VerificationCode;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Cookie on 2019/1/17.
 */
@RestController
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;
    private UserService userService;
    public UserController userController;

    @Autowired
    public UserController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isEmailNO(String email) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }


    @PostMapping(value = "/weixinlogin")
    public Object weixinlogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        System.out.println(password);
        user.setPassword(password);
        System.out.println(username);
        User userInDataBase = userService.findByUsername(username);
        System.out.println(userInDataBase + "===========");
        if (userInDataBase == null) {
            jsonObject.put("code", "400");
            jsonObject.put("message", "用户不存在");
        } else {
            if (!userService.comparePassword(user, userInDataBase)) {
                jsonObject.put("code", "401");
                jsonObject.put("message", "密码错误");
            } else {
                String token = authenticationService.getToken(userInDataBase);
                Login loginInfo = userService.findByUserId(userInDataBase.getId());
                if (loginInfo == null) {
                    Login login = new Login();
                    login.setUserid(userInDataBase.getId());
                    login.setToken(token);
                    userService.addToken(login);
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "首次登陆成功");
                    jsonObject.put("token", token);
                    jsonObject.put("data", userInDataBase);
                } else {
                    loginInfo.setToken(token);
                    userService.updateToken(loginInfo);
                    jsonObject.put("code", "201");
                    jsonObject.put("message", "再次登陆成功");
                    jsonObject.put("token", token);
                    jsonObject.put("data", userInDataBase);
                }
            }
        }
        return jsonObject;
    }


    @PostMapping(value = "/relogin")
    public Object relogin(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {

        System.out.println("------------" + username + "---" + password);
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        user.setPassword(password);
        User userInDataBase = userService.findByUsername(username);
        if (userInDataBase == null) {
            jsonObject.put("code", "400");
            jsonObject.put("message", "用户不存在");
        } else {
            if (!userService.comparePassword(user, userInDataBase)) {
                jsonObject.put("code", "401");
                jsonObject.put("message", "密码错误");
            } else {
                String token = authenticationService.getToken(userInDataBase);
                Login loginInfo = userService.findByUserId(userInDataBase.getId());
                if (loginInfo == null) {
                    Login login = new Login();
                    login.setUserid(userInDataBase.getId());
                    login.setToken(token);
                    userService.addToken(login);
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "首次登陆成功");
                    jsonObject.put("token", token);
                    jsonObject.put("data", userInDataBase);
                } else {
                    loginInfo.setToken(token);
                    userService.updateToken(loginInfo);
                    jsonObject.put("code", "201");
                    jsonObject.put("message", "再次登陆成功");
                    jsonObject.put("token", token);
                    jsonObject.put("data", userInDataBase);
                }
            }
        }
        return jsonObject;
    }

    @PostMapping(value = "/login")
    public Object login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("signcode") String signcode) {
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        String signcodeSession = (String) session.getAttribute("signcode");
        System.out.println("sessionhou:" + session.getId());
        System.out.println("signcode==>" + signcode);
        System.out.println("signcodeSession==>" + signcodeSession);

        if (StringUtils.isEmpty(signcode)) {
            jsonObject.put("code", "402");
            jsonObject.put("message", "前端验证码为空");
        }

        if (StringUtils.isEmpty(signcodeSession)) {
            jsonObject.put("code", "403");
            jsonObject.put("message", "后台验证码为空");
        }

        //验证的时候不区分大小写
        if (signcode.equalsIgnoreCase(signcodeSession)) {
            User user = new User();
            user.setPassword(password);
            User userInDataBase = userService.findByUsername(username);
            if (userInDataBase == null) {
                jsonObject.put("code", "400");
                jsonObject.put("message", "用户不存在");
            } else {
                if (!userService.comparePassword(user, userInDataBase)) {
                    jsonObject.put("code", "401");
                    jsonObject.put("message", "密码错误");
                } else {
                    String token = authenticationService.getToken(userInDataBase);
                    Login loginInfo = userService.findByUserId(userInDataBase.getId());
                    if (loginInfo == null) {
                        Login login = new Login();
                        login.setUserid(userInDataBase.getId());
                        login.setToken(token);
                        userService.addToken(login);
                        jsonObject.put("code", "200");
                        jsonObject.put("message", "首次登陆成功");
                        jsonObject.put("token", token);
                        jsonObject.put("data", userInDataBase);
                    } else {
                        loginInfo.setToken(token);
                        userService.updateToken(loginInfo);
                        jsonObject.put("code", "201");
                        jsonObject.put("message", "再次登陆成功");
                        jsonObject.put("token", token);
                        jsonObject.put("data", userInDataBase);
                    }
                }
            }
        } else {
            jsonObject.put("code", "405");
            jsonObject.put("message", "验证码错误");
        }

        return jsonObject;
    }

    /**
     * 验证图片验证码
     *
     * @param request
     * @param signcode
     * @return
     */
    @RequestMapping("/check")
    @ResponseBody
    public JSONObject check(HttpServletRequest request, String signcode) {
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        String signcodeSession = (String) session.getAttribute("signcode");
        System.out.println("sessionhou:" + session.getId());
        System.out.println("signcode==>" + signcode);
        System.out.println("signcodeSession==>" + signcodeSession);

        if (StringUtils.isEmpty(signcode)) {
            jsonObject.put("code", 401);
            jsonObject.put("message", "前端验证码为空");
        }

        if (StringUtils.isEmpty(signcodeSession)) {
            jsonObject.put("code", 403);
            jsonObject.put("message", "后台验证码为空");
        }

        //验证的时候不区分大小写
        if (signcode.equalsIgnoreCase(signcodeSession)) {
            jsonObject.put("code", 200);
            jsonObject.put("message", "OK");
        } else {
            jsonObject.put("code", 405);
            jsonObject.put("message", "验证码错误");
        }
        return jsonObject;
    }


    /**
     * 注册用户 jxy
     *
     * @param user
     * @return
     */

    @PostMapping(value = "/register")
    public Object addOne(User user) {


        JSONObject jsonObject = new JSONObject();
        if (userService.findByUsername(user.getUsername()) != null) {
            jsonObject.put("code", "400");
            jsonObject.put("message", "该用户名已被注册");
            return jsonObject;
        } else if (!isRMobile(user.getMobile())) {
            jsonObject.put("code", "404");
            jsonObject.put("message", "该手机号已经被占用！");
            return jsonObject;
        } else if (user.getUsername().length() < 2) {
            jsonObject.put("code", "401");
            jsonObject.put("message", "用户名长度太短");
            return jsonObject;
        } else if (user.getPassword().length() < 2) {
            jsonObject.put("code", "402");
            jsonObject.put("message", "密码长度太短");
            return jsonObject;
        }
//        else if(user.getMobile()!=null||!user.getMobile().equals("")){
//            if(!isEmailNO(user.getEmail())){
//                jsonObject.put("code", "404");
//                jsonObject.put("message", "不是邮箱格式");
//                return jsonObject;
//            }
//        }else if(user.getEmail()!=null||!user.getEmail().equals("")){
//            if(!isMobileNO(user.getMobile())){
//                jsonObject.put("code", "403");
//                jsonObject.put("message", "不是手机号格式");
//                return jsonObject;
//            }
//        }else if(user.getMobile()==null||user.getMobile().equals(""))
//            if(user.getEmail()==null||user.getEmail().equals("")){
//                jsonObject.put("code", "200");
//                jsonObject.put("message", "注册成功");
//                userService.addUser(user);
//                return jsonObject;
//            }
        user.setAvatar("http://39.105.50.119:8080/avatar.png");
        jsonObject.put("code", "200");
        jsonObject.put("message", "注册成功");
        userService.addUser(user);
        return jsonObject;
    }


    /**
     * jxy 专家注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/registerExperts")
    public JSONObject addExperts(User user) {
        JSONObject jsonObject = new JSONObject();
        if (!isRMobile(user.getMobile())) {
            jsonObject.put("code", "400");
            jsonObject.put("message", "该手机号已被注册");
            return jsonObject;
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            jsonObject.put("code", "400");
            jsonObject.put("message", "该用户名已被注册");
            return jsonObject;
        } else {
            user.setAvatar("http://39.105.50.119:8080/avatar.png");
            jsonObject.put("code", "200");
            jsonObject.put("message", "注册成功");
            userService.addExperts(user);
            Experts experts = new Experts();
            experts.setExpertsid(user.getId());
            experts.setName(user.getName());
            experts.setSex(user.getSex());
            experts.setType(user.getExpertstag());
            experts.setResearch(user.getResearch());
            experts.setClassification(user.getClassification());
            experts.setAchievement(user.getResearch());
            experts.setMobile(user.getMobile());
            experts.setAddress(user.getArea());
            experts.setEmail(user.getEmail());
            experts.setImg(user.getAvatar());
            userService.insertExperts(experts);
            return jsonObject;
        }
    }

    //    @PostMapping("/update")
//    public Object updateOne(User user,@RequestParam ("token") String token){
//        JSONObject jsonObject = new JSONObject();
//        Login userid = userService.findUseridByToken(token);
//        if(userid==null || userid.equals("")){
//            //用户不存在则ID为空
//            jsonObject.put("code", "501");
//            jsonObject.put("message", "没有此用户");
//            return jsonObject;
//        }
//        userService.updateUser(user);
//        User userInDataBase = userService.findById(userid.getUserid());
//        jsonObject.put("code", "200");
//        jsonObject.put("message", "修改成功");
//        jsonObject.put("user", userInDataBase);
//        return jsonObject;
//    }
    @PostMapping("/updateUserinfo")
    public Object updateUserinfo(User user, ServletRequest request) {
        System.out.println(user.getRole());
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if (token == null) {
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        } else {
            Login login = userService.findUseridByToken(token);
            if (login == null) {
                jsonObject.put("code", "406");
                jsonObject.put("message", "查询失败");
            } else {
                User userInDataBase = userService.findById(login.getUserid());
                if (userInDataBase == null) {
                    jsonObject.put("code", "500");
                    jsonObject.put("message", "修改失败");
                } else {
                    user.setId(userInDataBase.getId());
                    userService.updateUser(user);
                    userInDataBase = userService.findById(login.getUserid());
                    if (userInDataBase.getRole() == 100) {
                        Experts experts = new Experts();
                        experts.setExpertsid(userInDataBase.getId());
                        experts.setEmail(userInDataBase.getEmail());
                        experts.setAddress(userInDataBase.getArea());
                        experts.setMobile(userInDataBase.getMobile());
                        experts.setName(userInDataBase.getName());
                        experts.setSex(userInDataBase.getSex());
                        userService.updateExperts(experts);
                    }
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "修改成功");
                    jsonObject.put("data", userInDataBase);
                }
            }
        }
        return jsonObject;
    }

    @PostMapping("/update1")
    public Object updateOne1(User user, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        userService.updateUser(user);
        User userInDataBase = userService.findById(id);
        jsonObject.put("code", "200");
        jsonObject.put("message", "修改成功");
        jsonObject.put("user", userInDataBase);
        return jsonObject;
    }

    @PostMapping("/update2")
    public Object update2() {
        JSONObject jsonObject = new JSONObject();
        List<User> userInDataBase = userService.findExpert();
        User user = new User();
        for (int i = 0; i < userInDataBase.size(); i++) {
            user.setId(userInDataBase.get(i).getId());
            user.setRole(100);
            userService.updateUser(user);
        }
        jsonObject.put("code", "200");
        jsonObject.put("message", "修改成功");
        return jsonObject;
    }

    /**
     * jxy 更改密码
     */
    @PostMapping(value = "/changePassword")
    public Object changePassword(String oldPassword, String newPassword, ServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if (token == null) {
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        } else {
            Login loginuser = userService.findUseridByToken(token);
            if (loginuser == null) {
                jsonObject.put("code", "501");
                jsonObject.put("message", "没有此用户");
            } else {
                //数据库中的user信息
                User user = userService.findById(loginuser.getUserid());

                //从数据库返回的密码
                String dbOldPassword = user.getPassword();

                //从数据库中修改的密码
                System.out.println(oldPassword);
                if (dbOldPassword.equals(userService.passwordToHash(oldPassword))) {
                    user.setPassword(userService.passwordToHash(newPassword));
                    //真正操作数据库
                    userService.changePassword(user);
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "修改成功");
                } else {
                    jsonObject.put("code", "401");
                    jsonObject.put("message", "原始密码不对");
                }
            }
        }
        return jsonObject;
    }

    /**
     * jxy 忘记密码
     *
     * @return
     */
    @PostMapping(value = "/forgetPassword")
    public Object forgetPassword(@RequestParam("mobile") String mobile, @RequestParam("newPassword") String newPassword) {
        JSONObject jsonObject = new JSONObject();
        //数据库中的user信息
        User user = userService.findByUserPhoneNum(mobile);
        if (user == null) {
            jsonObject.put("code", "501");
            jsonObject.put("message", "没有此用户");
        } else {
            //修改用户信息
            user.setPassword(userService.passwordToHash(newPassword));
            //真正操作数据库
            userService.changePassword(user);
            jsonObject.put("code", "200");
            jsonObject.put("message", "修改成功");
        }
        return jsonObject;
    }

    /**
     * jxy
     * 判断数据库中是否有手机号  做页面用
     */
    @PostMapping(value = "/isMobile")
    public Object isMobile(@RequestParam("mobile") String mobile) {
        JSONObject jsonObject = new JSONObject();
        //数据库中的user信息
        User user = userService.findByUserPhoneNum(mobile);
        if (user == null) {
            jsonObject.put("code", "501");
            jsonObject.put("message", "没有此用户");
        } else {
            jsonObject.put("code", "200");
            jsonObject.put("message", "存在此用户");
        }
        return jsonObject;
    }

    /**
     * 判断是否有 手机号码 注册用
     * jxy
     *
     * @param mobile
     * @return
     */
    public boolean isRMobile(@RequestParam("mobile") String mobile) {
        //数据库中的user信息
        User user = userService.findByUserPhoneNum(mobile);
        System.out.println(user);
        if (user == null) {
            //表示没有此用户
            return true;
        } else {
            //表示有此用户 不能放行
            return false;
        }
    }


    /**
     * @param request
     * @return
     */

    @PostMapping(value = "/self")
    public Object findById(ServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if (token == null) {
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        } else {
//        String token = authenticationService.getToken(userInDataBase);
//        if(token == login.getToken()){
            Login loginuser = userService.findUseridByToken(token);
            //Login表内根据token获取用户ID
            if (loginuser == null || loginuser.equals("")) {
                //用户不存在则ID为空
                jsonObject.put("code", "501");
                jsonObject.put("message", "没有此用户");
            } else {
                int news = 0;
                User userInDataBase = userService.findById(loginuser.getUserid());
                List<Invite> inviteInfo = userService.checkUnReadQuestionList(userInDataBase.getId());
                if (inviteInfo.size() != 0) {
                    for (int i = 0; i < inviteInfo.size(); i++) {
                        if (inviteInfo.get(i).getIsread() == 0) {
                            news++;
                            userInDataBase.setNews(news);
                        }
                    }
                }
                jsonObject.put("code", 200);
                jsonObject.put("message", "获取个人信息成功");
                jsonObject.put("data", userInDataBase);
            }

        }
        return jsonObject;
    }

    @PostMapping(value = "/getUserInfoById")
    public Object getUserInfoById(@RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        User user = userService.findById(id);
        if (user != null) {
            jsonObject.put("code", 200);
            jsonObject.put("message", "success");
            jsonObject.put("data", user);
        } else {
            jsonObject.put("code", 406);
            jsonObject.put("message", "fail");
        }
        return jsonObject;
    }

    @GetMapping(value = "/getVerify")
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VerificationCode verificationCode = new VerificationCode();
        //获取验证码图片
        BufferedImage image = verificationCode.getImage();
        //获取验证码内容
        String text = verificationCode.getText();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        randomCode.append(text);
        // 将验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("signcode", randomCode.toString());
        System.out.println("sessionqian:" + session.getId());
        System.out.println("session-signcode==>" + randomCode.toString());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }


    //    邀请专家
    @PostMapping(value = "/recommendByKeywords")
    public Object recommendByKeywords(String keyword) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (keyword.length() == 0) {
            jsonObject.put("code", "400");
            jsonObject.put("message", "关键字为空");
            jsonObject.put("data", userService.findExpert());
        } else {

            List<User> arr = new ArrayList<User>();
            List<User> expertsInfo = userService.recommendByKeywords(keyword);
            System.out.println("++++123123123" + expertsInfo);
            if (expertsInfo == null) {
                jsonObject.put("code", "400");
                jsonObject.put("message", "找不到相关专家");
                jsonObject.put("data", userService.findExpert());
            } else {
                System.out.println("++++11111111111111" + expertsInfo.size());
                for (int i = 0; i < expertsInfo.size(); i++) {
                    if (expertsInfo.get(i).getRole() != null) {
                        if (expertsInfo.get(i).getRole().equals(100)) {
                            arr.add(expertsInfo.get(i));
                        }
                    }


                }
                jsonObject.put("code", "201");
                jsonObject.put("message", "找到相关专家");
                jsonObject.put("data", arr);
            }
        }
        return jsonObject;
    }


}


