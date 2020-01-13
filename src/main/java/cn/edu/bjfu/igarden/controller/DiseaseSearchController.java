package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.service.DiseaseTuiliSolr;
import cn.edu.bjfu.igarden.service.InsectSearchService;
import cn.edu.bjfu.igarden.service.PlantService;
import cn.edu.bjfu.igarden.service.PlantSolrService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by wxy on 2019/5/23.
 */
@RestController
public class DiseaseSearchController {
    InsectSearchService insectSearchService;
    @Autowired
    PlantSolrService plantSolrService;

    @Autowired
    PlantService plantService;

    @Autowired
    DiseaseTuiliSolr diseaseTuiliSolr;

    @Autowired
    private DiseaseSearchController(InsectSearchService insectSearchService) {
        this.insectSearchService = insectSearchService;
    }


    @PostMapping(value = "/searchInsectListByKeyword")
    public Object searchInsectListByKeyword(String keyword) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (keyword.replaceAll("\\s*", "").length() == 0) {
            jsonObject.put("code", "202");
            jsonObject.put("message", "输入内容为空");
        } else {
            List<InsectSearchEntity> insectSearchEntityList = insectSearchService.recommendInsectByKeywords(keyword);
            if (insectSearchEntityList.size() == 0) {
                jsonObject.put("code", "201");
                jsonObject.put("message", "找不到相关内容");
            } else {
                jsonObject.put("code", "200");
                jsonObject.put("message", "找到搜索结果");
                jsonObject.put("data", insectSearchEntityList);
            }

        }

        return jsonObject;
    }


    @PostMapping(value = "/searchChinaListByKeyword")
    public Object searchChinaListByKeyword(String keyword) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (keyword.replaceAll("\\s*", "").length() == 0) {
            jsonObject.put("code", "202");
            jsonObject.put("message", "输入内容为空");
        } else {
            List<InsectSearchEntity> insectSearchEntityList = insectSearchService.recommendChinayuanlinByKeywords(keyword);
            if (insectSearchEntityList.size() == 0) {
                jsonObject.put("code", "201");
                jsonObject.put("message", "找不到相关内容");
            } else {
                jsonObject.put("code", "200");
                jsonObject.put("message", "找到搜索结果");
                jsonObject.put("data", insectSearchEntityList);
            }

        }

        return jsonObject;
    }

    /**
     * Created by wxy on 2019/5/23.
     */
    @PostMapping(value = "/InitialTuili")
    public Object InitialTuili( String china_disease,String china_buwei,String china_weihaizz,String china_zhengzhuang,String plant_bieming, HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        DiseaseTuili tuili=new DiseaseTuili();
        tuili.setChina_disease(china_disease);
        tuili.setChina_buwei(china_buwei);
        tuili.setChina_weihaizz(china_weihaizz);
        tuili.setChina_zhengzhuang(china_zhengzhuang);
        String plant_name = plantService.queryplantnameByplantbieming(plant_bieming);
        System.out.println("plant_name" + plant_name);
        if (plant_name != null) {
            List<sql_sort> sql_sort = plantService.queryChinayuanidByplantname(plant_name);
            //System.out.println("所有的"+sql_sort);
            List<String> Disease_name = new ArrayList<String>();
            List<String> Solr_name = new ArrayList<String>();
            for (int i = 0; i < sql_sort.size(); i++) {
                //查询出sql_sort中的chinayuanlin_id
                int chinayuanlin_id = sql_sort.get(i).getChinayuanlin_id();
                //根据chinayuanlin_id查询出植物病害详情
                Disease_name.add(plantService.queryChinayuanidBychinayuanlinId(chinayuanlin_id).getTitle());
            }
            System.out.println(plant_bieming);
            System.out.println(tuili.toString());
            List<DiseaseTuili> list = diseaseTuiliSolr.SearchDiseaseByTuili(tuili);
            if (null !=list) {
                for (int i = 0; i < list.size(); i++) {
                    Solr_name.add(list.get(i).getChina_title());
                }
                String[] Solr_array = Solr_name.toArray(new String[Solr_name.size()]);
                String[] Disease_array = Disease_name.toArray(new String[Disease_name.size()]);
                String[] DiseaseList_array = getJ(Disease_array, Solr_array);
                if (DiseaseList_array.length > 1) {
                    Disease_name = Arrays.asList(DiseaseList_array);
                    HttpSession session = request.getSession();
                    session.setAttribute("Diseasename", Disease_name);
                    System.out.println("sessionqian:" + session.getId());
                    System.out.println("session-Diseasename==>" + session.getAttribute("Diseasename"));
                    List<disease_inference> allRule = new ArrayList<disease_inference>();
                    List<disease_rule> Rule = new ArrayList<disease_rule>();
                    //根据名称查询所有的规则
                    for (int i = 0; i < Disease_name.size(); i++) {
                        allRule.add(diseaseTuiliSolr.searchInference(Disease_name.get(i)));
                    }
                    System.out.println(allRule.toString());
                    List<Integer>listNums = new ArrayList<>();
                    for(int i=0;i<allRule.size();i++){
                        listNums.add(allRule.get(i).getRule1());
                    }
                    //去重
                    List<Integer> ruleList = new ArrayList<Integer>(new LinkedHashSet<Integer>(listNums));
                    //根据规则查询每一条规则详情
                    for (int i = 0; i < ruleList.size(); i++) {
                        Rule.add(diseaseTuiliSolr.searchAllrule(ruleList.get(i)));
                    }

//                    session.setAttribute("Rule", Rule);

//            for (int i = 0; i < list.size(); i++) {
//                for (int j = 0; j < Disease_name.size(); j++) {
//                    //System.out.println("是不是真的"+Disease_name.get(j)+"------"+list.get(i).getChina_title()+Disease_name.get(j).equals(list.get(i).getChina_title()));
//                    if (Disease_name.get(j).equals(list.get(i).getChina_title())) {
//                        DiseaseList.add(Disease_name.get(j));
//                        //System.out.println("传输的"+DiseaseList);
//                    }
//
//                }
//            }
                    //System.out.println("后来的"+DiseaseList);
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "找到相关咨询");
                    jsonObject.put("data", Rule);

                } else if (DiseaseList_array.length == 1) {
                    Disease_name = Arrays.asList(DiseaseList_array);
                    jsonObject.put("code", "202");
                    jsonObject.put("message", "找到唯一咨询");
                    jsonObject.put("data", Disease_name);
                } else {
                    jsonObject.put("code", "201");
                    jsonObject.put("message", "未找到相关咨询");
                }
            } else {
                jsonObject.put("code", "201");
                jsonObject.put("message", "未找到相关咨询");
            }
        } else {
            jsonObject.put("code", "201");
            jsonObject.put("message", "未找到相关咨询");
        }

        return jsonObject;
    }

    /**
     * ruleNum 进行到第几步
     * id 返回的id为多少
     * jxy
     * @return
     */
     @PostMapping(value = "/XunhuanTuili")
     public Object XunhuanTuili(int ruleNum,int ruleid,HttpServletRequest request){
         JSONObject jsonObject = new JSONObject();
         HttpSession session = request.getSession();
         List<String> diseasenames = (List<String>)session.getAttribute("Diseasename");
         List<disease_inference> allRule = new ArrayList<disease_inference>();
         List<String> disease_name = new ArrayList<String>();
         //根据diseasenames 查询其中的id
         for (int i = 0; i < diseasenames.size(); i++) {
             allRule.add(diseaseTuiliSolr.searchInference(diseasenames.get(i)));
         }

         //根据传进来的规则Id 与查询的id进行对比
         for(int i=0;i<allRule.size();i++){
             if(ruleNum==1){
                 if(allRule.get(i).getRule1()==ruleid){
                     //查询本条数据
                     disease_name.add(allRule.get(i).getResult());
                 }
             }else if(ruleNum==2){
                 if(allRule.get(i).getRule2()==ruleid){
                     //查询本条数据
                     disease_name.add(allRule.get(i).getResult());
                 }
             }else if(ruleNum==3){
                 if(allRule.get(i).getRule3()==ruleid){
                     //查询本条数据
                     disease_name.add(allRule.get(i).getResult());
                 }
             }else if(ruleNum==4){
                 if(allRule.get(i).getRule4()==ruleid){
                     //查询本条数据
                     disease_name.add(allRule.get(i).getResult());
                 }
             }else if(ruleNum==5){
                 if(allRule.get(i).getRule5()==ruleid){
                     //查询本条数据
                     disease_name.add(allRule.get(i).getResult());
                 }
             }else if(ruleNum==6){
                 if(allRule.get(i).getRule6()==ruleid){
                     //查询本条数据
                     disease_name.add(allRule.get(i).getResult());
                 }
             }else if(ruleNum==7){
                 if(allRule.get(i).getRule7()==ruleid){
                     //查询本条数据
                     disease_name.add(allRule.get(i).getResult());
                 }
             }else if(ruleNum==8){
                 if(allRule.get(i).getRule8()==ruleid){
                     //查询本条数据
                     disease_name.add(allRule.get(i).getResult());
                 }
             }
         }
         if(disease_name.size()==1){
             jsonObject.put("code", "202");
             jsonObject.put("message", "找到相关咨询");
             jsonObject.put("data",disease_name);
         }else{
              session.setAttribute("Diseasename",disease_name);
             List<disease_inference> allRuleNew = new ArrayList<disease_inference>();
             for (int i = 0; i < disease_name.size(); i++) {
                 allRuleNew.add(diseaseTuiliSolr.searchInference(disease_name.get(i)));
             }

             List<Integer> nums = new ArrayList<>();
             for(int i=0;i<allRuleNew.size();i++){
                 if(ruleNum==1){
                          nums.add(allRuleNew.get(i).getRule2());
                 }else if(ruleNum==2){
                         nums.add(allRuleNew.get(i).getRule3());
                 }else if(ruleNum==3){
                         nums.add(allRuleNew.get(i).getRule4());
                 }else if(ruleNum==4){
                         nums.add(allRuleNew.get(i).getRule5());
                 }else if(ruleNum==5){
                         nums.add(allRuleNew.get(i).getRule6());
                 }else if(ruleNum==6){
                        nums.add(allRuleNew.get(i).getRule7());
                 }else if(ruleNum==7) {
                     nums.add(allRuleNew.get(i).getRule8());
                 }
             }
             List<Integer> distinctNums = new ArrayList<Integer>(new LinkedHashSet<Integer>(nums));

             List<disease_rule> disease_rules = new ArrayList<>();
             for(int i=0;i<distinctNums.size();i++){
                 disease_rules.add(diseaseTuiliSolr.searchAllrule(distinctNums.get(i)));
             }

             jsonObject.put("code", "200");
             jsonObject.put("message", "找到相关咨询");
             jsonObject.put("data",disease_rules);
         }
         return jsonObject;

     }



    private static String[] getJ(String[] m, String[] n) {
        List<String> rs = new ArrayList<String>();

        // 将较长的数组转换为set
        Set<String> set = new HashSet<String>(Arrays.asList(m.length > n.length ? m : n));

        // 遍历较短的数组，实现最少循环
        for (String i : m.length > n.length ? n : m) {
            if (set.contains(i)) {
                rs.add(i);
            }
        }

        String[] arr = {};
        return rs.toArray(arr);
    }

    @PostMapping(value = "/searchDiseaseQuestion")
    public Object searchQuestion(String keyword) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (keyword.replaceAll("\\s*", "").length() == 0) {
            jsonObject.put("code", "202");
            jsonObject.put("message", "输入内容为空");
        } else {
            if (keyword.length() != 0) {
                List<Question> list1 = new ArrayList<Question>();
                List<Question> list = plantSolrService.searchQuestion(keyword);
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getQtype() == 1)
                            list1.add(list.get(i));
                    }
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "找到相关咨询");
                    jsonObject.put("data", list1);
                } else {
                    jsonObject.put("code", "201");
                    jsonObject.put("message", "未找到相关咨询");
                }

            }
        }
        return jsonObject;
    }


}
