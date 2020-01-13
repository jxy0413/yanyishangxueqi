package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.Homenews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yxy on 2019/4/4.
 */
//@Repository
public interface HomenewsRepository extends JpaRepository<Homenews,Integer> {
    Homenews findNewsById(int id);

}
