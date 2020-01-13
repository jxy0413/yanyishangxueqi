package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.ChinayuanlinDetail;
import cn.edu.bjfu.igarden.entity.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxy on 2019/3/7.
 */
@Mapper
public interface CollectMapper {
    List<Collect> getCollectListByType(Collect collect);
    int addCollect(Collect collect);
    int deleteCollect(Collect collect);
    Collect findCollectByUseridAndX(Collect collect);
    ArrayList<ChinayuanlinDetail> findAllchinayuanlindetail();
    ArrayList<ChinayuanlinDetail> findTop();
}
