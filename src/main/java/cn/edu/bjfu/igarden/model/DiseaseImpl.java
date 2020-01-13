package cn.edu.bjfu.igarden.model;

import cn.edu.bjfu.igarden.dao.DiseaseRepository;
import cn.edu.bjfu.igarden.dao.PlantRepository;
import cn.edu.bjfu.igarden.entity.DiseaseTable;
import cn.edu.bjfu.igarden.util.LogUtil;
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
import java.util.List;

@Component
public class DiseaseImpl {
    private static final int SHORT_ITEM_NUM = 5;
    private static final int ITEM_NUM = 10;
    @Autowired
    DiseaseRepository diseaseRepository;
    @Autowired
    PlantRepository plantRepository;
    @Autowired
    EntityManager entityManager;

    public DiseaseTable getDisease(int id) {
        return diseaseRepository.findByIdAndDeleteTime(id, 0);
    }

    public List getDiseaseList(int type, String name, int page) {
        if (name.contains("'")) {
            name = name.replaceAll("'", "");
        }
        // 多参数where子句组装
        String[] names = name.trim().split(" ");
        String baseWhere = "d.disease_name like '%%%s%%'";
        String and = " and ";
        StringBuilder whereBuilder = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                whereBuilder.append(String.format(baseWhere, names[i]));
            } else {
                whereBuilder.append(String.format(baseWhere, names[i])).append(and);
            }
        }

        String baseQuery = "select d.id, d.disease_name name, d.disease_description description, p.plant_name plantName, d.disease_image imageUrl " +
                "from disease d inner join plant p on d.plant_id = p.id where d.delete_time = 0 and p.delete_time = 0 and " +
                "(select (@rowNum\\:=@rowNum+1) from (Select (@rowNum \\:=0) ) b) > (%1$d * %2$d) and d.disease_type = %3$d and %4$s order by d.hits desc, d.update_time, d.id asc limit %2$d";
        String mQuery = String.format(baseQuery, page - 1, ITEM_NUM, type, whereBuilder.toString());

        // 自定义query，提高查询效率
        Query query = entityManager.createNativeQuery(mQuery);
        // 转换查询方式，返回key-value形式
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();
    }

    /**
     * 根据查询次数、更新时间排序，分页获取病虫害列表数据
     *
     * @param type 0->病害 1->虫害
     * @param page 页数
     * @return 返回分页列表数据
     */
    public Page<DiseaseTable> findAll(int type, int page) {
        List<Sort.Order> list = new ArrayList<>();
        list.add(new Sort.Order(Sort.Direction.DESC, "hits"));
        list.add(new Sort.Order(Sort.Direction.DESC, "updateTime"));
        Pageable pageable = new PageRequest(page - 1, ITEM_NUM, new Sort(list));
        return diseaseRepository.findByDeleteTimeAndDiseaseType(type, 0, pageable);
    }

    public void save(DiseaseTable diseaseTable) {
        // 记录查询次数
        diseaseTable.hitsPlus();
        diseaseRepository.save(diseaseTable);
    }

    public List getPlantList(String name, int page, int size) {
        if (name.contains("'")) {
            name = name.replaceAll("'", "");
        }
        // 多参数where子句组装
        String[] names = name.trim().split(" ");
        String baseWhere = "p.plant_name like '%%%s%%'";
        String baseWhere2 = "another_name.name like '%%%s%%'";
        String and = " and ";
        StringBuilder whereBuilder = new StringBuilder();
        StringBuilder whereBuilder2 = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                whereBuilder.append(String.format(baseWhere, names[i]));
                whereBuilder2.append(String.format(baseWhere2, names[i]));
            } else {
                whereBuilder.append(String.format(baseWhere, names[i])).append(and);
                whereBuilder2.append(String.format(baseWhere2, names[i])).append(and);
            }
        }

        int item_num = size == 0 ? SHORT_ITEM_NUM : ITEM_NUM;
        String baseQuery = "select distinct p.id, p.plant_name, p.plant_description description, p.plant_family family, p.plant_genus genus, p.plant_image imageUrl " +
                "from plant p,another_name  where p.id=another_name.plant_id and p.delete_time = 0 and %2$s or %1$s";
        String mQuery = String.format(baseQuery, whereBuilder.toString(),whereBuilder2.toString());
        System.out.println(mQuery);
        // 自定义query，提高查询效率
        Query query = entityManager.createNativeQuery(mQuery);
        // 转换查询方式，返回key-value形式
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();
    }

    // 缺少一个病虫害对应多个植物id的实现，当前仅实现一对一关系
    public List getDiseasesByPlantId(int id, int page, int type) {
        String baseQuery = "select d.id, d.disease_name name, d.disease_description description, d.disease_part part, d.disease_image imageUrl " +
                "from disease d where d.plant_id = %1$d and d.delete_time = 0 and d.disease_type = %2$d order by d.hits desc, d.update_time, d.id asc limit %3$d, %4$d";
        String mQuery = String.format(baseQuery, id, type, (page - 1) * ITEM_NUM, ITEM_NUM);

        // 自定义query，提高查询效率
        Query query = entityManager.createNativeQuery(mQuery);
        // 转换查询方式，返回key-value形式
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();
    }
}
