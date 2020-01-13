package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.DiseaseTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiseaseRepository extends JpaRepository<DiseaseTable, Integer> {

    DiseaseTable findByIdAndDeleteTime(int id, long deleteTime);
    Page<DiseaseTable> findByDeleteTimeAndDiseaseType(long deleteTime, int diseaseType, Pageable pageable);
    DiseaseTable findByDiseaseOptionRelAndDeleteTime(int optionId, long deleteTime);
}
