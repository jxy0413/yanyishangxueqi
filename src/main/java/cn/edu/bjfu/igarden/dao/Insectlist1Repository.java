package cn.edu.bjfu.igarden.dao;


import cn.edu.bjfu.igarden.entity.Insectlist1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yxy on 2019/1/24.
 */

public interface Insectlist1Repository extends JpaRepository<Insectlist1,Integer> {
    Insectlist1 findById(Integer id);

    List<Insectlist1> findByKind1(String kind1);



}
