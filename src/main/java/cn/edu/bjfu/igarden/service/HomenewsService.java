package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.dao.HomenewsMapper;
import cn.edu.bjfu.igarden.entity.Homenews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yxy on 2019/4/8.
 */
@Service
public class HomenewsService {
    private HomenewsMapper homenewsMapper;
    @Autowired
    public HomenewsService(HomenewsMapper homenewsMapper) {
        this.homenewsMapper = homenewsMapper;
    }
    public List<Homenews> getNewsList(){
        return this.homenewsMapper.getNewsList();
    }

}

