package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.ImgClassifyResult;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by yxy on 2019/5/18.
 */
@Mapper
public interface DiseaseclassifyresultMapper {
    int addDiseaseClassifyResult(ImgClassifyResult imgClassifyResult);
}
