package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.DiseaseOptionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiseaseOptionRepository extends JpaRepository<DiseaseOptionTable, Integer> {

    List<DiseaseOptionTable> findByQuestionIdAndDeleteTime(int questionId, long deleteTime);
}
