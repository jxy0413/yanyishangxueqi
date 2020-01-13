package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.DiseaseQuestionTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseQuestionRepository extends JpaRepository<DiseaseQuestionTable, Integer> {
    DiseaseQuestionTable findByPlantIdAndDiseaseTypeAndQuestionSequenceAndDeleteTime(int plantId, int diseaseType, int sequence, long deleteTime);
    DiseaseQuestionTable findByIdAndDeleteTime(int id, long deleteTime);
}
