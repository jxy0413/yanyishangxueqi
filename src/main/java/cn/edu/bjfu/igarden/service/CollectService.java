package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.dao.CollectMapper;
import cn.edu.bjfu.igarden.entity.ChinayuanlinDetail;
import cn.edu.bjfu.igarden.entity.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxy on 2019/3/7.
 */
@Service
public class CollectService {


    private CollectMapper collectMapper;
    @Autowired
    public CollectService(CollectMapper collectMapper){this.collectMapper=collectMapper;}

    public List<Collect> getCollectListByType(int type,int userid){
        Collect collect = new Collect();
        collect.setCollecttype(type);
        collect.setUserid(userid);
        return collectMapper.getCollectListByType(collect);
    }

    public int addCollect(Collect collect){
        return  collectMapper.addCollect(collect);
    }
    public int deleteCollect(Collect collect){
//        Collect collect = new Collect();
//        collect.setId(id);
        return collectMapper.deleteCollect(collect);
    }

    public Collect findCollectByUseridAndX(Collect collect){
        return collectMapper.findCollectByUseridAndX(collect);
    }
    public ArrayList<ChinayuanlinDetail> findAllchinayuanlindetail(){
        return collectMapper.findAllchinayuanlindetail();
    }
    public ArrayList<ChinayuanlinDetail> findTop(){
        return collectMapper.findTop();
    }


}
