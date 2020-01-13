package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.dao.EndangeredPlantMapper;
import cn.edu.bjfu.igarden.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cookie on 2019/5/6.
 */
@Service
public class EndangeredPlantService {
    private EndangeredPlantMapper endangeredPlantMapper;

    @Autowired
    public EndangeredPlantService(EndangeredPlantMapper endangeredPlantMapper) {
        this.endangeredPlantMapper = endangeredPlantMapper;
    }
    public List findListByType(){
        return endangeredPlantMapper.findListByType();
    }
    public PlantFamilyTypeTable findPlantType(String genus){
        PlantFamilyTypeTable plantFamilyTypeTable=new PlantFamilyTypeTable();
        plantFamilyTypeTable.setGenus(genus);
        return endangeredPlantMapper.findPlantType(plantFamilyTypeTable);
    }
    public PlantNumberTable findByPlantNumber(String plant_number){
        PlantNumberTable plantNumberTable=new PlantNumberTable();
        plantNumberTable.setPlant_number(plant_number);
        return endangeredPlantMapper.findByPlantNumber(plantNumberTable);
    }
    public  int  updatePlantNumber(int id,String number){
        PlantTable plantTable =new PlantTable();
        plantTable.setId(id);
        plantTable.setPlant_number(number);
        return  endangeredPlantMapper.updatePlantNumber(plantTable);
    }
    public  int insertPlantsFamily(PlantFamilyTypeTable plantFamilyTypeTable){
        return endangeredPlantMapper.insertPlantsFamily(plantFamilyTypeTable);
    }
    public  List<PlantNumberTable> findAllGenus(){
        return endangeredPlantMapper.findAllGenus();
    }

    public EndangeredPlantsTable findEndangeredPlantsById(int id){
        EndangeredPlantsTable endangeredPlantsTable=new EndangeredPlantsTable();
        endangeredPlantsTable.setId(id);
        return endangeredPlantMapper.findEndangeredPlantsById(endangeredPlantsTable);
    }
    public List<EndangeredPlantsTable> findEndangeredPlantsListByType(String type){
        EndangeredPlantsTable endangeredPlantsTable =new EndangeredPlantsTable();
        endangeredPlantsTable.setType(type);
        return endangeredPlantMapper.findEndangeredPlantsListByType(endangeredPlantsTable);
    }
//    public List<EndangeredPlantsTable> findAll(){
//        return endangeredPlantMapper.findAll();
//    }
    public  int changeAll(EndangeredPlantsTable endangeredPlantsTable){
        return endangeredPlantMapper.changeAll(endangeredPlantsTable);
    }
}
