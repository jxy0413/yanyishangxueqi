package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.service.ExpertsService;
import cn.edu.bjfu.igarden.service.UserService;
import com.alibaba.fastjson.JSONObject;
import javafx.geometry.Pos;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yxy on 2019/5/6.
 */
@RestController
public class ExpertsController {
    public static final int PAGESIZE = 10;

    private Experts experts;
    private ExpertsService expertsService;
    private UserService userService;
    public ExpertsController(ExpertsService expertsService,
                             UserService userService){
        this.expertsService = expertsService;
        this.userService = userService;

    }

    @PostMapping(value = "/getAllExperts")
    public Object getAllExperts(){
//        Pageable pageable = new PageRequest(page,PAGESIZE);
//        Iterator<Experts>iterator = expertsService.getAllExperts(pageable).iterator();
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("message","成功");
        jsonObject.put("data",expertsService.getAllExperts());
        return jsonObject;
    }

    @PostMapping(value = "/getExpertsListByType")
    public Object getExpertsListByType(@RequestParam("type")String type){
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("message","成功");
        jsonObject.put("data",expertsService.getExpertsLstByType(type));
        return jsonObject;
    }

    @PostMapping(value = "/getExpertById")
    public Object getExpertById(@RequestParam("id")int id){
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("message","成功");
        jsonObject.put("data",expertsService.getExpertsById(id));
        return jsonObject;
    }
    @PostMapping(value = "/getExpertByName")
    public Object getExpertByName(@RequestParam("name")String name){
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("message","成功");
        jsonObject.put("data",expertsService.getExertsByName(name));
        return jsonObject;
    }

    @PostMapping(value = "/getInvitedExpertslist")
    public Object getInvitedExpertslist(@RequestParam("ids")String ids){

        List<String> expertId = Arrays.asList(ids.split("#"));
        List<User> expertsList = new ArrayList<>();
        for(int i=0;i<expertId.size();i++){
         User user=userService.findById(Integer.parseInt(expertId.get(i).toString()));
            User temp = new User();
            temp.setName(user.getName());
            temp.setAvatar(user.getAvatar());
            temp.setId(user.getId());
            expertsList.add(temp);
        }
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("message","成功");
        jsonObject.put("data",expertsList);
        return jsonObject;
    }
    /**
     * 获取当前用户下所有聊天信息
     */
    @PostMapping(value = "/getChat")
    public Object getChat(@RequestParam("userid")int userid) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        List<List<chat>> chatList = new ArrayList<>();
        chatList = expertsService.getChat(userid);
        if(chatList.size()!=0){
            for (int i = 0; i < chatList.size(); i++) {
                Collections.sort(chatList.get(i),new Comparator<chat>() {

                    @Override
                    public int compare(chat o1, chat o2) {
                        return o2.getTime().compareTo(o1.getTime());
                    }
                });

                for (int j = 0; j < chatList.get(i).size(); j++) {
                    if (chatList.get(i).get(j).getTime() != null) {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = fmt.parse(chatList.get(i).get(j).getTime());
                        String s = fmt.format(date);
                        chatList.get(i).get(j).setTime(s);
                    }
                    System.out.println(chatList.get(i).get(j).getUserid());
                    System.out.println(userService.findById(chatList.get(i).get(j).getUserid()).getName());
                    if(userid != chatList.get(i).get(j).getUserid()){
                        chatList.get(i).get(j).setFromUsername(userService.findById(chatList.get(i).get(j).getUserid()).getName());
                        chatList.get(i).get(j).setFromUserAvatar(userService.findById(chatList.get(i).get(j).getUserid()).getAvatar());
                    }else {
                        chatList.get(i).get(j).setFromUsername(userService.findById(chatList.get(i).get(j).getTouserid()).getName());
                        chatList.get(i).get(j).setFromUserAvatar(userService.findById(chatList.get(i).get(j).getTouserid()).getAvatar());
                    }

                }
            }

            jsonObject.put("code", 200);
            jsonObject.put("message", "成功");
            jsonObject.put("data", chatList);
        }else{
            jsonObject.put("code", 202);
            jsonObject.put("message", "暂无聊天记录");
        }



        return jsonObject;

    }
    /**
     * 与专家进行聊天
     */
    /**
     * 获取当前用户下所有聊天信息
     */
    @PostMapping(value = "/chatWithExpert")
    public Object getChat(@RequestParam("userid")int userid,@RequestParam("expertid")int expertid) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        List<List<chat>> chatList = new ArrayList<>();
        chatList = expertsService.getChatWithExpert(userid,expertid);
        if(chatList.size()!=0){
            for (int i = 0; i < chatList.size(); i++) {
                for (int j = 0; j < chatList.get(i).size(); j++) {
                    if (chatList.get(i).get(j).getTime() != null) {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = fmt.parse(chatList.get(i).get(j).getTime());
                        String s = fmt.format(date);
                        chatList.get(i).get(j).setTime(s);
                    }
                    System.out.println(chatList.get(i).get(j).getUserid());
                    System.out.println(userService.findById(chatList.get(i).get(j).getUserid()).getName());
                    chatList.get(i).get(j).setFromUsername(userService.findById(chatList.get(i).get(j).getUserid()).getName());
                    chatList.get(i).get(j).setFromUserAvatar(userService.findById(chatList.get(i).get(j).getUserid()).getAvatar());
                }
            }
            jsonObject.put("code", 200);
            jsonObject.put("message", "成功");
            jsonObject.put("data", chatList);
        }else{
            jsonObject.put("code", 202);
            jsonObject.put("message", "暂无聊天记录");
        }


        return jsonObject;

    }






    /**
     * 进入聊天内容后全部变成已读
     */
    @PostMapping(value = "/updateReadChat")
    public Object readChat(@RequestParam("userid")int userid,@RequestParam("touserid")int touserid) {
        List<List<chat>> chatList = new ArrayList<>();
        chatList = expertsService.getChatWithExpert(userid,touserid);
        if(chatList.size()!=0){
        expertsService.readChat(userid,touserid);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("message", "成功");
        return jsonObject;
    }

    }
