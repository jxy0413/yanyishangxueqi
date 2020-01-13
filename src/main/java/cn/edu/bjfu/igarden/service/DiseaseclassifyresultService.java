package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.dao.DiseaseclassifyresultMapper;
import cn.edu.bjfu.igarden.entity.ImgClassifyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yxy on 2019/5/18.
 */
@Service
public class DiseaseclassifyresultService {
    private DiseaseclassifyresultMapper diseaseclassifyresultMapper;
    @Autowired
    public DiseaseclassifyresultService(DiseaseclassifyresultMapper diseaseclassifyresultMapper){
        this.diseaseclassifyresultMapper=diseaseclassifyresultMapper;
    }

    public int addDiseaseClassifyResult(ImgClassifyResult imgClassifyResult){
        return this.diseaseclassifyresultMapper.addDiseaseClassifyResult(imgClassifyResult);
    }
}
