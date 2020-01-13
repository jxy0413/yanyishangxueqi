package cn.edu.bjfu.igarden.controller;
import cn.edu.bjfu.igarden.dao.InsectDetailRepository;
import cn.edu.bjfu.igarden.dao.Insectlist1Repository;
import cn.edu.bjfu.igarden.dao.Insectlist2Repository;
import cn.edu.bjfu.igarden.dao.Insectlist3Repository;

import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.service.InsectSearchService;
import cn.edu.bjfu.igarden.service.PlantService;
import cn.edu.bjfu.igarden.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yxy on 2019/2/12.
 */
@RestController
public class InsectController {

    //设置分页查询的页面大小
    public static final int PAGESIZE = 15;

    @Autowired
    private Insectlist1Repository insectlist1Repository;

    @Autowired
    private Insectlist2Repository insectlist2Repository;

    @Autowired
    private Insectlist3Repository insectlist3Repository;

    @Autowired
    private InsectDetailRepository insectDetailRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private InsectSearchService insectSearchService;

    @Autowired
    private PlantService plantService;
//    @Autowired
//    public ChinayuanlindetailRepository chinayuanlindetailRepository;


    @PostMapping(value = "/getInsect1list")
    public BaseEntity getInsect1list() {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(insectlist1Repository.findAll());
        return baseEntity;
    }

    @PostMapping(value = "/getInsect2list")
    public BaseEntity getInsect2list(@RequestParam("kind1") String kind1) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(insectlist2Repository.findInsect2Bykind1(kind1));
        return baseEntity;
    }

    @PostMapping(value = "/getInsect3list")
    public BaseEntity getInsect3list(@RequestParam("kind2") String kind2) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(insectlist3Repository.findInsect3Bykind2(kind2));
        return baseEntity;
    }

    @PostMapping(value = "/getInsectDetailByKind3")
    public BaseEntity getInsectDetailByKind3(@RequestParam("kind3") String kind3) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        baseEntity.setMessage("success");
        baseEntity.setData(insectDetailRepository.findInsectDetailByKind3(kind3));
        return baseEntity;

//        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
//        Iterator<InsectDetail> all  = insectDetailRepository.findInsectDetaillistByKind3(pageable,kind3).iterator();
//        List<InsectDetail>list = new ArrayList<>();
//        while (all.hasNext()){
//            list.add(all.next());
//        }
//        if(all == null){
//            baseEntity.setMessage("查询失败");
//            return baseEntity;
//        }else {
//            baseEntity.setMessage("success");
//            baseEntity.setData(list);
//            return baseEntity;
//        }

    }

    @PostMapping(value = "/getInsectDetailByTitle")
    public BaseEntity getInsectDetailByTitle(@RequestParam("title") String title) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(insectDetailRepository.findInsectDetailByTitle(title));
        return baseEntity;
    }
    @PostMapping(value = "/getInsectDetailById")
    public BaseEntity getInsectDetailById(@RequestParam("id")int id){
        BaseEntity<InsectDetail>baseEntity =new BaseEntity<>();
        baseEntity.setData(insectDetailRepository.findOne(id));
        baseEntity.setMessage("success");
        baseEntity.setCode(200);
        return  baseEntity;
    }

    @PostMapping(value = "/getInsectDetailList")
    public BaseEntity getInsectDetailList(@RequestParam("page") int page) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page, PAGESIZE, Sort.Direction.ASC, "id");
        Iterator<InsectDetail> all = insectDetailRepository.findAll(pageable).iterator();
        List<InsectDetail> list = new ArrayList<>();
        while (all.hasNext()) {
            list.add(all.next());
        }
        if (all == null) {
            baseEntity.setMessage("查询失败");
            return baseEntity;
        } else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }

    @PostMapping(value = "/searchInsectDetail")
    public BaseEntity searchInsectDetail(@RequestParam("title") String search) {
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
//        List list = new ArrayList<>();
//        list = chinayuanlindetailRepository.searchChinayuanlinDetailByTitle(search);
//        list.addAll(insectDetailRepository.findInsectDetailByTitle(search));
//        baseEntity.setData(list);
        baseEntity.setData(insectDetailRepository.findByTitle(search));
        return baseEntity;
    }

    /**
     * jxy
     * @param id
     * @return
     */
    @PostMapping(value = "/getPlantDetailById")
    public Object getPlantDetailById(@Param("id")int id){
        System.out.println(id);
        JSONObject jsonObject = new JSONObject();
        //数据库中根据id的植物信息
        PlantDetail plant  = plantService.rearchDetailById(id);

        if(plant==null) {
            jsonObject.put("code", "501");
            jsonObject.put("message", "此植物没有详情");
        }else{
            jsonObject.put("code", "200");
            jsonObject.put("message", "此植物存在详情");
            jsonObject.put("data",plant);
        }
        return jsonObject;

    }

//    @PostMapping(value = "/solrSearchInsect")
//    public Object solrSearchInsect(String keyword) throws Exception{
//        JSONObject jsonObject=new JSONObject();
//        List<InsectDetail> list=insectSearchService.recommendInsectByKeywords(keyword);
//        jsonObject.put("code", "200");
//        jsonObject.put("message", "找到相关农业病虫害");
//        jsonObject.put("data", list);
//        return jsonObject;
//    }
//    @PostMapping(value = "/solrSearchChina")
//    public Object solrSearchChina(String keyword) throws Exception{
//        JSONObject jsonObject=new JSONObject();
//        List<ChinayuanlinDetail> list=insectSearchService.recommendChinayuanlinByKeywords(keyword);
//        jsonObject.put("code", "200");
//        jsonObject.put("message", "找到相关农业病虫害");
//        jsonObject.put("data", list);
//        return jsonObject;
//    }

    @PostMapping(value = "/insectDetailReadTimesAdd")
    public BaseEntity insectDetailReadTimesAdd(ServletRequest servletRequest, @RequestParam("id") int id) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("token");
        BaseEntity baseEntity = new BaseEntity();
        if (token == null) {
            baseEntity.setCode(210);
            baseEntity.setMessage("无token");
        } else {
            Login loginInfo = userService.findUseridByToken(token);
            if (loginInfo == null) {
                baseEntity.setCode(211);
                baseEntity.setMessage("找不到登录信息");
            } else {
                baseEntity.setCode(200);
                baseEntity.setMessage("success");
                InsectDetail insectDetail = insectDetailRepository.findOne(id);
                insectDetail.setReadtimes(insectDetail.getReadtimes() + 5);
                InsectDetail saveone = insectDetailRepository.save(insectDetail);
                saveone.setTimenow(saveone.getTimenow().substring(0,19));
                baseEntity.setData(saveone);
            }
        }
        return baseEntity;
    }

}

