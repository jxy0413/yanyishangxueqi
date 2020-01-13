package cn.edu.bjfu.igarden.dao;


import cn.edu.bjfu.igarden.entity.Chinayuanlinlistdibei;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
public interface ChinayuanlinlistdibeiRepository extends JpaRepository<Chinayuanlinlistdibei,Integer>{
    List<Chinayuanlinlistdibei>findByZclass(String zclass);
    List<Chinayuanlinlistdibei>findByZbc(Pageable pageable, String zbc);
    Chinayuanlinlistdibei findByZname(String zname);
//    List<Chinayuanlinlistdibei>getDibeilist(Pageable pageable);

}
