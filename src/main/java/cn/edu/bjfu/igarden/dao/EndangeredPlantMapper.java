package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Cookie on 2019/5/6.
 */
@Mapper
public interface EndangeredPlantMapper {
   List findListByType();
   List<PlantNumberTable> findAllFamily();
   List<PlantNumberTable> findAllGenus();
   PlantFamilyTypeTable findFamily(PlantFamilyTypeTable plantFamilyTypeTable);
   EndangeredPlantsTable findEndangeredPlantsById(EndangeredPlantsTable endangeredPlantsTable);
   PlantFamilyTypeTable findPlantType(PlantFamilyTypeTable plantFamilyTypeTable);
   List<EndangeredPlantsTable> findEndangeredPlantsListByType(EndangeredPlantsTable endangeredPlantsTable);
   List<EndangeredPlantsTable> findAll();
   PlantNumberTable findByPlantNumber(PlantNumberTable plantNumberTable);
   int changeAll(EndangeredPlantsTable endangeredPlantsTable);
   int insertPlantsFamily(PlantFamilyTypeTable plantFamilyTypeTable);
   int updatePlantNumber(PlantTable plantTable);
   int updatePlantId(PlantTable plantTable);
}
