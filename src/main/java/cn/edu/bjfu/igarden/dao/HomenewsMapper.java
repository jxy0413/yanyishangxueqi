package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.Homenews;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by yxy on 2019/4/8.
 */
@Mapper
public interface HomenewsMapper {
    List<Homenews>getNewsList();
}
