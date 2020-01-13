package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.Insectlist2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
public interface Insectlist2Repository extends JpaRepository<Insectlist2,Integer>{
    List<Insectlist2>findInsect2Bykind1(String kind1);
}
