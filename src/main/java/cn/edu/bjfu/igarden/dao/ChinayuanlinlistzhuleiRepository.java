package cn.edu.bjfu.igarden.dao;
import cn.edu.bjfu.igarden.entity.Chinayuanlinlistzhulei;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
public interface ChinayuanlinlistzhuleiRepository extends JpaRepository<Chinayuanlinlistzhulei,Integer>{
    List<Chinayuanlinlistzhulei>findByZclass(String zclass);
    List<Chinayuanlinlistzhulei>findByZbc(Pageable pageable, String zbc);
    Chinayuanlinlistzhulei findByZname(String zname);
//    List<Chinayuanlinlistzhulei>getZhuleilist(Pageable pageable);

}
