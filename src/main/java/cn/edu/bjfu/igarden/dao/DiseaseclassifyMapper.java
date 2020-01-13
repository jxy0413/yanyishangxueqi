package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.ImgClassify;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by yxy on 2019/5/19.
 */
@Mapper
public interface DiseaseclassifyMapper {
    int addDiseaseClassifyByUserId(ImgClassify imgClassify);
}
