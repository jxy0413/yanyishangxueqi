package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.ImgClassify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yxy on 2019/5/19.
 */
@Repository
public interface ShibieRepository extends JpaRepository<ImgClassify,Integer>{

}
