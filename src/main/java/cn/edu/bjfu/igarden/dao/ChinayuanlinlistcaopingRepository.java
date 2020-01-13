package cn.edu.bjfu.igarden.dao;


import cn.edu.bjfu.igarden.entity.Chinayuanlinlistcaoping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
public interface ChinayuanlinlistcaopingRepository extends JpaRepository<Chinayuanlinlistcaoping,Integer>{
    List<Chinayuanlinlistcaoping>findCaopingByZbc(Pageable pageable,String zbc);
    List<Chinayuanlinlistcaoping>findCaopingByZname(String zname);
//    List<Chinayuanlinlistcaoping>getCaopinglist(Pageable pageable);
}
