package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.Chinayuanlinlistflower;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
public interface ChinayuanlinlistflowerRepository extends JpaRepository<Chinayuanlinlistflower,Integer> {
    List<Chinayuanlinlistflower>findFlowerByZbc(Pageable pageable, String zbc);
    List<Chinayuanlinlistflower>findFlowerByZname(String zname);
}
