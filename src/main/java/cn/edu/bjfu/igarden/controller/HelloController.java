package cn.edu.bjfu.igarden.controller;
import cn.edu.bjfu.igarden.dao.Insectlist1Repository;
import cn.edu.bjfu.igarden.entity.BaseEntity;
import cn.edu.bjfu.igarden.entity.Insectlist1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yxy on 2019/1/22.
 */

@RestController
public class HelloController {

    @Autowired
    Insectlist1Repository insectlist1Repository;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }

//    @ResponseBody
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","你好");
        return "success";
    }

    @PostMapping(value = "/findall")
    public List<Insectlist1> findall(){

           List<Insectlist1> list = insectlist1Repository.findAll();
        return list;
    }
    @GetMapping(value = "/findinsect")
    public BaseEntity findinsect(){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setData(insectlist1Repository.findAll());
        baseEntity.setMessage("hahaha");
        baseEntity.setCode(200);
        return baseEntity;
    }
}
