package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.dao.ChinayuanlindetailRepository;
import cn.edu.bjfu.igarden.entity.Collect;
import cn.edu.bjfu.igarden.entity.Login;
import cn.edu.bjfu.igarden.service.CollectService;
import cn.edu.bjfu.igarden.service.UserService;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.List;

/**
 * Created by yxy on 2019/3/7.
 */
@RestController
public class CollectController {
    private CollectService collectService;
    private UserService userService;

    @Autowired
    public CollectController(CollectService collectService, UserService userService) {
        this.collectService = collectService;
        this.userService = userService;
    }
    @Autowired
    ChinayuanlindetailRepository chinayuanlindetailRepository;

    @PostMapping(value = "/getCollectListByType")
    public Object getCollectListByType(ServletRequest servletRequest, @RequestParam("type") int type) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("token");
        JSONObject jsonObject = new JSONObject();

        if (token == null) {
            jsonObject.put("code", 405);
            jsonObject.put("message", "无token！");
        } else {
            Login login = userService.findUseridByToken(token);
            if (login == null) {
                jsonObject.put("code", 406);
                jsonObject.put("message", "token失效");
                return jsonObject;
            } else {

                List<Collect> list = collectService.getCollectListByType(type, login.getUserid());
                if (type == 3){
                    for (int i=0;i<list.size();i++){
                       list.get(i).setDisease(chinayuanlindetailRepository.findChinayuanlinDetailById(list.get(i).getChinayuanlinid()).getDisease());
                       if (list.get(i).getDisease()==null){
                           list.get(i).setDisease("病害");
                       }
                    }
                }
                jsonObject.put("code", 200);
                jsonObject.put("message", "success");
                jsonObject.put("data", list);
                System.out.println(list);
                return jsonObject;
            }
        }

        return jsonObject;
    }

    @PostMapping(value = "/addCollect")
    public JSONObject addCollect(ServletRequest servletRequest, Collect collect) {
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest) servletRequest;
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
                collect.setUserid(login.getUserid());
                System.out.println("用户ID" + collect.getUserid());
                int flag = collectService.addCollect(collect);

//                if (flag != -1) {
                jsonObject.put("code", 200);
                jsonObject.put("message", "success");
                jsonObject.put("data", flag);
//                } else {
//                    jsonObject.put("code", 201);
//                    jsonObject.put("message", "插入失败");
//                }
            }
        }
        return jsonObject;
    }

    @PostMapping(value = "/deleteCollect")
    public JSONObject deleteCollect(ServletRequest servletRequest, Collect collect) {

        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest) servletRequest;
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
                jsonObject.put("code", 200);
                jsonObject.put("message", "success");
                collect.setUserid(login.getUserid());
                int flag = collectService.deleteCollect(collect);
                jsonObject.put("data", flag);
            }
        }
        return jsonObject;
    }

    @PostMapping(value = "/findCollectByUseridAndX")
    public JSONObject findCollectByUseridAndX(ServletRequest servletRequest, Collect collect) {
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest) servletRequest;
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
                collect.setUserid(login.getUserid());
                Collect collectres = collectService.findCollectByUseridAndX(collect);
                if (collectres != null) {
                    jsonObject.put("code", 200);
                    jsonObject.put("message", "收藏该信息");
                    jsonObject.put("isCollected",true);
                } else {
                    jsonObject.put("code", 201);
                    jsonObject.put("message", "未收藏该信息");
                    jsonObject.put("isCollected",false);
                }
            }
        }
        return  jsonObject;
    }
}
