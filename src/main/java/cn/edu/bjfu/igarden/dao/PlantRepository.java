package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.PlantTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<PlantTable, Integer> {
    PlantTable findByPlantName(String name);

    PlantTable findByIdAndDeleteTime(int id, long deleteTime);

    Page<PlantTable> findByPlantGenusAndDeleteTime(String genus, long deleteTime, Pageable pageable);
}
