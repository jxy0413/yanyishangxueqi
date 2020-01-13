package cn.edu.bjfu.igarden.dao;
import cn.edu.bjfu.igarden.entity.InsectDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
public interface InsectDetailRepository  extends JpaRepository<InsectDetail,Integer>{
//    List<InsectDetail>findInsectDetaillistByKind3(Pageable pageable,String kind3);
    List<InsectDetail>findInsectDetailByKind3(String kind3);
    List<InsectDetail> findInsectDetailByTitle(String title);

    @Query(value = "select * from insectdetail u where u.title like CONCAT('%',:title,'%')",nativeQuery = true)
    List<InsectDetail>findByTitle(@Param("title")String title);

}
