package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.ChinayuanlinDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */

@Repository
public interface ChinayuanlindetailRepository extends JpaRepository<ChinayuanlinDetail,Integer>{
    ChinayuanlinDetail findChinayuanlinDetailById(int id);
    ChinayuanlinDetail findChinayuanlinDetailByTitle(String title);
//    List<ChinayuanlinDetail> searchChinayuanlinDetailByTitle(String title);

    @Query(value = "select * from chinayuanlindetail as u where u.title like CONCAT('%',:title,'%') ",nativeQuery = true)
    List<ChinayuanlinDetail>findChinayuanlinDetailByTitleLike( @Param("title") String title);

    @Query(value = "select * from chinayuanlindetail u where u.title=?1",nativeQuery = true)
    List<ChinayuanlinDetail>findByTitle(String title);
}
