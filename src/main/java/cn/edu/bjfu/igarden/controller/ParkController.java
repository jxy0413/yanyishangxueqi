package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.EndangeredPlantsTable;
import cn.edu.bjfu.igarden.entity.Flowerinfo;
import cn.edu.bjfu.igarden.entity.Parkinfo;
import cn.edu.bjfu.igarden.entity.ParkinfoWithBLOBs;
import cn.edu.bjfu.igarden.service.ParkService;
import com.alibaba.fastjson.JSONObject;
import groovy.transform.ASTTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jxy on 2019/10/25.
 */
@RestController
public class ParkController {
     @Autowired
     private ParkService parkService;

    @PostMapping("/getParkList")
    public Object getParkList(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",parkService.getParkList());
        return  jsonObject;
    }
    @GetMapping(value = "/getParkQueryList")
    public Object getParkQueryList(@RequestParam("name")String name,@RequestParam("type")String type,@RequestParam("zone")String zone) {
        JSONObject jsonObject=new JSONObject();
        List<ParkinfoWithBLOBs> list = parkService.getParkQueryList(name,type,zone);
        jsonObject.put("code",200);
        jsonObject.put("success","成功获取公园列表");
        jsonObject.put("data",list);
        return jsonObject;
    }
    @PostMapping("/getFlowerList")
    public Object getFlowerList(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",parkService.getFlowerList());
        return  jsonObject;
    }
    @GetMapping(value = "/getFlowerQueryList")
    public Object getFlowerQueryList(@RequestParam("name")String name,@RequestParam("bloomingtime")String bloomingtime) {
        JSONObject jsonObject=new JSONObject();
        List<Flowerinfo> list = parkService.getFlowerQueryList(name,bloomingtime);
        jsonObject.put("code",200);
        jsonObject.put("success","成功获取花卉列表");
        jsonObject.put("data",list);
        return jsonObject;
    }
}
