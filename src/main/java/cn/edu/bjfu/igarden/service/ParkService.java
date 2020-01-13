package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.dao.AnswerMapper;
import cn.edu.bjfu.igarden.entity.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jxy on 2019/10/25.
 */
@Service
public class ParkService {
    @Autowired
    private AnswerMapper plantDetailMapper;

    public Object getParkList() {
        List<ParkinfoWithBLOBs> parkList=plantDetailMapper.selectByExampleWithBLOBs();
        return parkList;
    }
    public List<ParkinfoWithBLOBs> getParkQueryList(String name,String type,String zone){
        ParkinfoWithBLOBs parkinfoWithBLOBs =new ParkinfoWithBLOBs();
        parkinfoWithBLOBs.setName(name);
        parkinfoWithBLOBs.setType(type);
        parkinfoWithBLOBs.setZone(zone);
        return plantDetailMapper.findParkListByName(parkinfoWithBLOBs);
    }
    public Object getFlowerList() {
        List<Flowerinfo> flowerList=plantDetailMapper.selectByFlowerExample();
        return flowerList;
    }
    public List<Flowerinfo> getFlowerQueryList(String name,String bloomingtime){
        Flowerinfo flowerinfo =new Flowerinfo();
        flowerinfo.setName(name);
        flowerinfo.setBloomingtime(bloomingtime);
        return plantDetailMapper.findFlowerListByName(flowerinfo);
    }
}
