package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.BaseEntity;
import cn.edu.bjfu.igarden.entity.Province;
import cn.edu.bjfu.igarden.entity.SceneryTable;
import cn.edu.bjfu.igarden.model.SceneryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SceneryController {
    @Autowired
    SceneryImpl sceneryImpl;

    @GetMapping(value = "/getScenery")
    public BaseEntity<List<SceneryTable>> getScenery(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page") String page, @RequestParam(value = "proId") String proId) {
        BaseEntity<List<SceneryTable>> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        List<SceneryTable> list = sceneryImpl.getScenery(keyword, page, proId);
        if (list.size() != 0) {
            baseEntity.setData(list);
        }
        return baseEntity;
    }

    @GetMapping(value = "/getProvince")
    public BaseEntity<List<Province.ShowapiResBodyBean.ListBean>> getProvince() {
        BaseEntity<List<Province.ShowapiResBodyBean.ListBean>> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        List<Province.ShowapiResBodyBean.ListBean> list = sceneryImpl.getProvince().getShowapi_res_body().getList();
        if (list.size() != 0) {
            baseEntity.setData(list);
        }
        return baseEntity;
    }

    @GetMapping(value = "/getProvinceIdByName")
    public BaseEntity<String> getProvinceIdByName(String name) {
        BaseEntity<String> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        List<Province.ShowapiResBodyBean.ListBean> list = getProvince().getData();
        if (list == null) {
            baseEntity.setData("-1");
            return baseEntity;
        }
        for (Province.ShowapiResBodyBean.ListBean data : list) {
            if (name.equals(data.getName())) {
                baseEntity.setData(data.getId());
                return baseEntity;
            }
        }
        baseEntity.setData("-1");
        return baseEntity;
    }
}
