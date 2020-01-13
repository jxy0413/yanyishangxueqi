package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.Insectlist3;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
public interface Insectlist3Repository extends JpaRepository<Insectlist3,Integer>{
    List<Insectlist3>findInsect3Bykind2(String kind2);
}
