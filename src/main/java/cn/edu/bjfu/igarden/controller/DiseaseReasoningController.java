package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.BaseEntity;
import cn.edu.bjfu.igarden.entity.DiseaseQuestion;
import cn.edu.bjfu.igarden.entity.DiseaseTable;
import cn.edu.bjfu.igarden.model.DiseaseReasoningImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiseaseReasoningController {
    @Autowired
    DiseaseReasoningImpl diseaseReasoningImpl;

    /**
     * 获取病虫害推理第一题
     * @param plantId 植物id
     * @param type 0->病害 1->虫害
     * @return 返回题目信息
     */
    @GetMapping(value = "/getFirstDiseaseQuestion")
    public BaseEntity<DiseaseQuestion> getFirstDiseaseQuestion(@RequestParam(value = "plantId") int plantId, @RequestParam(value = "type") int type) {
        DiseaseQuestion diseaseQuestion = diseaseReasoningImpl.getDiseaseFirstQuestion(plantId, type);
        BaseEntity<DiseaseQuestion> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(diseaseQuestion);
        return baseEntity;
    }

    /**
     * 获取病虫害推理题目
     * @param id 题目id
     * @return 返回题目信息
     */
    @GetMapping(value = "/getDiseaseQuestion")
    public BaseEntity<DiseaseQuestion> getDiseaseQuestion(@RequestParam(value = "id") int id) {
        DiseaseQuestion diseaseQuestion = diseaseReasoningImpl.getDiseaseQuestion(id);
        BaseEntity<DiseaseQuestion> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(diseaseQuestion);
        return baseEntity;
    }

    /**
     * 通过最后一题选项id获取病虫害信息
     * @param id 选项id
     * @return 返回病虫害信息
     */
    @GetMapping(value = "/getDiseaseByOptionId")
    public BaseEntity<DiseaseTable> getDiseaseByOptionId(@RequestParam(value = "id") int id) {
        DiseaseTable diseaseTable = diseaseReasoningImpl.getDiseaseByOptionId(id);
        BaseEntity<DiseaseTable> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(diseaseTable);
        return baseEntity;
    }
}
