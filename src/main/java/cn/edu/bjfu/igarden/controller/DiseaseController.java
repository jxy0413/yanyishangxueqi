package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.BaseEntity;
import cn.edu.bjfu.igarden.entity.DiseaseTable;
import cn.edu.bjfu.igarden.model.DiseaseImpl;
import cn.edu.bjfu.igarden.model.SearchImpl;
import cn.edu.bjfu.igarden.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiseaseController {

    @Autowired
    DiseaseImpl diseaseImpl;
    @Autowired
    SearchImpl searchImpl;

    /**
     * 病害查询，支持多参数，以空格隔开
     *
     * @param name 输入参数
     * @param page 页数，从1开始
     * @return 病害列表
     */
    @GetMapping(value = "/getDiseaseList")
    public BaseEntity getDiseaseList(@RequestParam("name") String name, @RequestParam("page") int page) {
        LogUtil.d("name: " + name + " page: " + page);
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        List list = diseaseImpl.getDiseaseList(0, name, page);
        if (list.size() != 0) {
            baseEntity.setData(list);
        }

        // 搜索历史记录
        searchImpl.setSearch(name, 2);

        return baseEntity;
    }

    /**
     * 虫害查询，支持多参数，以空格隔开
     *
     * @param name 输入参数
     * @param page 页数，从1开始
     * @return 虫害列表
     */
    @GetMapping(value = "/getInsectList")
    public BaseEntity getInsectList(@RequestParam("name") String name, @RequestParam("page") int page) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        List list = diseaseImpl.getDiseaseList(1, name, page);
        if (list.size() != 0) {
            baseEntity.setData(list);
        }

        // 搜索历史记录
        searchImpl.setSearch(name, 2);

        return baseEntity;
    }

    /**
     * 获取病虫害详情
     *
     * @param id 病虫害id
     * @return 返回病虫害详情
     */
    @GetMapping(value = "/getDisease")
    public BaseEntity<DiseaseTable> getDisease(@RequestParam("id") int id) {
        BaseEntity<DiseaseTable> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");

        DiseaseTable diseaseTable = diseaseImpl.getDisease(id);
        baseEntity.setData(diseaseTable);

        diseaseImpl.save(diseaseTable);

        return baseEntity;
    }

    /**
     * 根据查询次数、更新时间排序，分页获取病害列表数据
     *
     * @param page 页数
     * @return 返回分页列表数据
     */
    @GetMapping(value = "/findAllDiseases")
    public BaseEntity<Page<DiseaseTable>> findAllDiseases(@RequestParam("page") int page) {
        BaseEntity<Page<DiseaseTable>> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(diseaseImpl.findAll(0, page));
        return baseEntity;
    }

    /**
     * 根据查询次数、更新时间排序，分页获取虫害列表数据
     *
     * @param page 页数
     * @return 返回分页列表数据
     */
    @GetMapping(value = "/findAllInsects")
    public BaseEntity<Page<DiseaseTable>> findAllInsects(@RequestParam("page") int page) {
        BaseEntity<Page<DiseaseTable>> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(diseaseImpl.findAll(1, page));
        return baseEntity;
    }

    /**
     * 病虫害搜索获取植物信息
     *
     * @param name 搜索输入内容
     * @param page 页数
     * @param size 0 -> 每页获取5条 1 -> 每页获取10条
     * @return 返回植物列表
     */
    @GetMapping(value = "/getMuchPlantsFromDisease")
    public BaseEntity getPlants(@RequestParam("name") String name, @RequestParam("page") int page, @RequestParam("size") int size) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        List list = diseaseImpl.getPlantList(name, page, size);
        if (list.size() != 0) {
            baseEntity.setData(list);
        }
        // 搜索历史记录
        searchImpl.setSearch(name, 1);
        return baseEntity;
    }
    /**
     * 病虫害搜索获取植物信息
     *
     * @param name 搜索输入内容
     * @param page 页数
     * @return 返回植物列表
     */
    @GetMapping(value = "/getPlantsFromDisease")
    public BaseEntity getPlants(@RequestParam("name") String name, @RequestParam("page") int page) {
        return getPlants(name, page, 0);
    }

    /**
     * 根据植物id获取病害信息
     *
     * @param id   植物id
     * @param page 页数
     * @return 返回病害列表
     */
    @GetMapping(value = "/getDiseasesByPlantId")
    public BaseEntity getDiseasesByPlantId(@RequestParam("id") int id, @RequestParam("page") int page) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        List list = diseaseImpl.getDiseasesByPlantId(id, page, 0);
        if (list.size() != 0) {
            baseEntity.setData(list);
        }
        return baseEntity;
    }

    /**
     * 根据植物id获取虫害信息
     *
     * @param id   植物id
     * @param page 页数
     * @return 返回虫害列表
     */
    @GetMapping(value = "getInsectsByPlantId")
    public BaseEntity getInsectsByPlantId(@RequestParam("id") int id, @RequestParam("page") int page) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        List list = diseaseImpl.getDiseasesByPlantId(id, page, 1);
        if (list.size() != 0) {
            baseEntity.setData(list);
        }
        return baseEntity;
    }
}
