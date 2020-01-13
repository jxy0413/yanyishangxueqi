package cn.edu.bjfu.igarden.model;

import cn.edu.bjfu.igarden.dao.PlantRepository;
import cn.edu.bjfu.igarden.entity.Plant;
import cn.edu.bjfu.igarden.entity.PlantDescription;
import cn.edu.bjfu.igarden.entity.PlantTable;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PlantImpl {
    private static final int ITEM_NUM = 10;
    @Autowired
    PlantRepository plantRepository;
    @Autowired
    EntityManager entityManager;

    public PlantDescription getDescription(int id) {
        PlantTable plantTable = plantRepository.findByIdAndDeleteTime(id, 0);
        PlantDescription plantDescription = new PlantDescription();
        plantDescription.setId(plantTable.getId());
        plantDescription.setPlantName(plantTable.getPlantName());
        plantDescription.setPlantDescription(plantTable.getPlantDescription().trim().equals("") ? plantTable.getPlantFbdq() : plantTable.getPlantDescription());
        plantDescription.setImageUrl(plantTable.getPlantImage());
        return plantDescription;
    }

    public List getPlantFamily(int page) {
        String baseQuery = "select DISTINCT a.plant_family family, (select count(*) from plant b where b.plant_family = a.plant_family) number from plant a where a.plant_family <> '' and delete_time = 0 limit %1$d, %2$d";
        String mQuery = String.format(baseQuery, (page - 1) * ITEM_NUM, ITEM_NUM);

        // 自定义query，提高查询效率
        Query query = entityManager.createNativeQuery(mQuery);
        // 转换查询方式，返回key-value形式
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();
    }

    public List getPlantGenus(String family, int page) {
        String baseQuery = "select DISTINCT a.plant_genus genus, (select count(*) from plant b where b.plant_genus = a.plant_genus) number from plant a where a.plant_family = '%1$s' and delete_time = 0 limit %2$d, %3$d";
        String mQuery = String.format(baseQuery, family, (page - 1) * ITEM_NUM, ITEM_NUM);

        // 自定义query，提高查询效率
        Query query = entityManager.createNativeQuery(mQuery);
        // 转换查询方式，返回key-value形式
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();
    }

    public List<PlantDescription> getPlantsByGenus(String genus, int page) {
        List<Sort.Order> list = new ArrayList<>();
        list.add(new Sort.Order(Sort.Direction.DESC, "hits"));
        list.add(new Sort.Order(Sort.Direction.DESC, "updateTime"));
        Pageable pageable = new PageRequest(page - 1, ITEM_NUM, new Sort(list));
        Page<PlantTable> plants = plantRepository.findByPlantGenusAndDeleteTime(genus, 0, pageable);

        List<PlantDescription> descriptions = new ArrayList<>();
        for (PlantTable plant : plants) {
            PlantDescription description = new PlantDescription();
            description.setId(plant.getId());
            description.setPlantName(plant.getPlantName());
            description.setPlantDescription(plant.getPlantDescription());
            description.setImageUrl(plant.getPlantImage());
            description.setFamily(plant.getPlantFamily());
            description.setGenus(plant.getPlantGenus());
            descriptions.add(description);
        }
        return descriptions;
    }

    public Plant.ResultBean getPlantById(int id) {
        Plant.ResultBean result = new Plant.ResultBean();
        PlantTable plantTable = plantRepository.findByIdAndDeleteTime(id, 0);
        result.setNameStd(plantTable.getPlantName());
        result.setNameLt(plantTable.getPlantLatinName());
        result.setAlias(plantTable.getPlantAlias());
        result.setDescription(plantTable.getPlantDescription());
        result.setFamilyCn(plantTable.getPlantFamily());
        result.setGenusCn(plantTable.getPlantGenus());
        result.setDevisionCn(plantTable.getDevision());
        List<String> images = Arrays.asList(plantTable.getPlantImage().split("#"));
        result.setImages(images);
        Plant.ResultBean.InfoBean infoBean = new Plant.ResultBean.InfoBean();
        infoBean.setYhjs(plantTable.getPlantYhjs());
        infoBean.setXgsc(plantTable.getPlantXgsc());
        infoBean.setJzgy(plantTable.getPlantJzgy());
        infoBean.setHksj(plantTable.getPlantHksj());
        infoBean.setFbdq(plantTable.getPlantFbdq());
        infoBean.setBxtz(plantTable.getPlantBxtz());
        result.setInfo(infoBean);
        return result;
    }

    public Plant.ResultBean getPlantByName(String name) {
        Plant.ResultBean result = new Plant.ResultBean();
        PlantTable plantTable = plantRepository.findByPlantName(name);
        result.setNameStd(plantTable.getPlantName());
        result.setNameLt(plantTable.getPlantLatinName());
        result.setAlias(plantTable.getPlantAlias());
        result.setDescription(plantTable.getPlantDescription());
        result.setFamilyCn(plantTable.getPlantFamily());
        result.setGenusCn(plantTable.getPlantGenus());
        result.setDevisionCn(plantTable.getDevision());
        List<String> images = Arrays.asList(plantTable.getPlantImage().split("#"));
        result.setImages(images);
        Plant.ResultBean.InfoBean infoBean = new Plant.ResultBean.InfoBean();
        infoBean.setYhjs(plantTable.getPlantYhjs());
        infoBean.setXgsc(plantTable.getPlantXgsc());
        infoBean.setJzgy(plantTable.getPlantJzgy());
        infoBean.setHksj(plantTable.getPlantHksj());
        infoBean.setFbdq(plantTable.getPlantFbdq());
        infoBean.setBxtz(plantTable.getPlantBxtz());
        result.setInfo(infoBean);
        return result;
    }

}
