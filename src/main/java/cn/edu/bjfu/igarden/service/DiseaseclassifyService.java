package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.dao.DiseaseclassifyMapper;
import cn.edu.bjfu.igarden.entity.ImgClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yxy on 2019/5/19.
 */
@Service
public class DiseaseclassifyService {
    private DiseaseclassifyMapper diseaseclassifyMapper;
    @Autowired
    public DiseaseclassifyService(DiseaseclassifyMapper diseaseclassifyMapper){
        this.diseaseclassifyMapper = diseaseclassifyMapper;
    }
    public int addDiseaseClassifyResult(ImgClassify imgClassify){
        return this.diseaseclassifyMapper.addDiseaseClassifyByUserId(imgClassify);
    }
}
