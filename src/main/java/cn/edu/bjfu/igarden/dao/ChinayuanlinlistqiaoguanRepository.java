package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.Chinayuanlinlistqiaoguan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
public interface ChinayuanlinlistqiaoguanRepository extends JpaRepository<Chinayuanlinlistqiaoguan,Integer>{
    List<Chinayuanlinlistqiaoguan>findByZclass(String zclass);
    List<Chinayuanlinlistqiaoguan>findByZbc(Pageable pageable, String zbc);
    Chinayuanlinlistqiaoguan findByZname(String zname);
//    List<Chinayuanlinlistqiaoguan>getQiaoguanlist(Pageable pageable);
}
