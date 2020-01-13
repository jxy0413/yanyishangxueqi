package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yxy on 2019/5/7.
 */
@Repository
public interface AlertRepository extends JpaRepository<Alert,Integer>{

}
