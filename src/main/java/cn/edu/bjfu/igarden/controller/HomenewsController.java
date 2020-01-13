package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.dao.HomenewsRepository;
import cn.edu.bjfu.igarden.entity.BaseEntity;
import cn.edu.bjfu.igarden.entity.Homenews;
import cn.edu.bjfu.igarden.service.HomenewsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yxy on 2019/4/4.
 */
@RestController
public class HomenewsController {
    @Autowired
    HomenewsRepository homenewsRepository;
    @Autowired
    HomenewsService homenewsService;


    @PostMapping(value = "/getNewsList")
    public JSONObject getNewsList(){
        JSONObject jsonObject = new JSONObject();
        List<Homenews>newslist = homenewsRepository.findAll();
        List<Homenews> outputlist = new ArrayList<>() ;
        Random random = new Random();
        for(int i=0;i<4;i++){
            outputlist.add(newslist.get(random.nextInt(151)));
        }
//        baseEntity.setData(newslist);
        jsonObject.put("code", 200);
        jsonObject.put("message", "success");
        jsonObject.put("data", outputlist);
        return  jsonObject;
    }

    @PostMapping(value = "/getNewsById")
    public BaseEntity getNewsById(@RequestParam("id")int id){
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setCode(200);
        baseEntity.setMessage("成功");
        baseEntity.setData(homenewsRepository.findNewsById(id));
        return baseEntity;
    }

    @PostMapping(value = "/getNews")
    public BaseEntity getNews(){
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setCode(200);
        baseEntity.setMessage("成功");
        baseEntity.setData(homenewsService.getNewsList());
        return baseEntity;
    }

}
