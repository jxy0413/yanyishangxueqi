package cn.edu.bjfu.igarden.dao;
import cn.edu.bjfu.igarden.entity.Chinayuanlinlistfruit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
public interface ChinayuanlinlistfruitRepository extends JpaRepository<Chinayuanlinlistfruit,Integer>{
    List<Chinayuanlinlistfruit>findByZclass(String zclass);
    List<Chinayuanlinlistfruit>findByZbc(Pageable pageable, String zbc);
    Chinayuanlinlistfruit findByZname(String zname);
//    List<Chinayuanlinlistfruit>getFruitlist(Pageable pageable);

}
