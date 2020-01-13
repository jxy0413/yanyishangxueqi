package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.SceneryTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SceneryRepository extends JpaRepository<SceneryTable, Integer> {
    SceneryTable findByName(String name);
}
