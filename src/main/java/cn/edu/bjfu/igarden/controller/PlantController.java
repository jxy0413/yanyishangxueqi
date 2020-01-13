package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.dao.PlantRepository;
import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.model.PlantImpl;
import cn.edu.bjfu.igarden.service.*;
import cn.edu.bjfu.igarden.tuijian.Recommend;
import cn.edu.bjfu.igarden.tuijian.UserSet;
import cn.edu.bjfu.igarden.util.SGPCResult;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class PlantController {
    @Autowired
    PlantImpl plantImpl;
    @Autowired
    EndangeredPlantService endangeredPlantService;
    @Autowired
    PlantRepository plantRepository;
    @Autowired
    PlantSolrService plantSolrService;
    @Autowired
    ExpertsService expertsService;
    @Autowired
    PlantService plantService;



    /**
     * 病虫害搜索获取某一植物信息
     * @param id 植物id
     */
    @GetMapping(value = "/getPlantDescription")
    public BaseEntity<PlantDescription> getDescription(@RequestParam(value = "id") int id) {
        BaseEntity<PlantDescription> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        PlantDescription description = plantImpl.getDescription(id);
        if (description.getPlantName() != null && description.getPlantDescription() != null) {
            baseEntity.setData(plantImpl.getDescription(id));
        }
        return baseEntity;
    }
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                System.out.print(f.getName() + ":");
                System.out.println(f.get(object));
                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * jxy  getType 无参数  返回各类植物类型下的植物，字段plantname planttpye
     * @return
     */
    @PostMapping (value = "/getType")
    public Object getType(){
        JSONObject jsonObject=new JSONObject();
        List<diseasePlant> diseasePlants=plantService.queryAll();
        jsonObject.put("data",diseasePlants);
        System.out.println(diseasePlants);
        return jsonObject;
    }


    /**
     *jxy
     * @return
     */
    @GetMapping(value = "/getDiseasecount")
    public Object getDisease(String plant_name){
        JSONObject jsonObject=new JSONObject();
        List<sql_sort> sql_sort=plantService.queryChinayuanidByplantname(plant_name);
        System.out.println(sql_sort);
        List<ChinayuanlinDetail> chinayuanlinDetail = new ArrayList<ChinayuanlinDetail>();
        for(int i=0;i<sql_sort.size();i++){
            //查询出sql_sort中的chinayuanlin_id
            int chinayuanlin_id = sql_sort.get(i).getChinayuanlin_id();
            //根据chinayuanlin_id查询出植物病害详情
            chinayuanlinDetail.add( plantService.queryChinayuanidBychinayuanlinId(chinayuanlin_id));

        }
        jsonObject.put("data",chinayuanlinDetail);
        return jsonObject;
    }


    /**
     *wxy
     * 返回植物别名（病虫害）
     * @return
     */
    @GetMapping(value = "/getPlantbieming")
    public Object getPlantbieming(String plant_name){
        JSONObject jsonObject=new JSONObject();
        List<plantbieming> plantbiemings=plantService.queryplantbiemingByplantname(plant_name);
        System.out.println(plantbiemings);
//        List<ChinayuanlinDetail> chinayuanlinDetail = new ArrayList<ChinayuanlinDetail>();
//        for(int i=0;i<sql_sort.size();i++){
//            //查询出sql_sort中的chinayuanlin_id
//            int chinayuanlin_id = sql_sort.get(i).getChinayuanlin_id();
//            //根据chinayuanlin_id查询出植物病害详情
//          chinayuanlinDetail.add( plantService.queryChinayuanidBychinayuanlinId(chinayuanlin_id));
//
//        }
        jsonObject.put("data",plantbiemings);
        return jsonObject;
    }



    /**
     * jxy
     * @param
     * @return
     */
    @GetMapping(value = "/countByZhiWu")
    public int countByZhiWu(String plant_name){
        System.out.println(plant_name);
        PlantDetail plantDetail=plantService.queryPlantByName(plant_name);

        if(plantDetail!=null){
            int shibieCount = plantDetail.getShibieCount();
            //修改数据库 每调用一次 数据库shibieCount 加1
            plantService.updateShiBieCount(plantDetail.getPlant_name(),shibieCount);
            return shibieCount+1;
        }
        return 0;  //没有的话 修改
    }


    /**
     * jxy
     * @param diseaseName
     * @return
     * 根据病虫害返回识别次数
     */
    @GetMapping("/countByBingChong")
    public Object countByBingChong(String diseaseName){
        System.out.println(diseaseName);
        JSONObject jsonObject =new JSONObject();

        DiseaseTable diseaseTable=plantService.queryBingChongByName(diseaseName);
        System.out.println(diseaseTable);

        if(diseaseTable!=null){
            int shibieCount = diseaseTable.getShibieCount();
            //修改数据库 每调用一次 数据库shibieCount 加1
            plantService.updateShiBieCountBingChong(diseaseTable.getDisease_name(),shibieCount);

            jsonObject.put("data",shibieCount+1);
        }
          //没有的话 修改
        return jsonObject;
    }

    /**
     * jxy
     * @param
     * @return
     * 根据病虫害返回识别次数
     */
    @GetMapping("/countByPlantName")
    public Object countByPlantName(String plantName){
        JSONObject jsonObject =new JSONObject();

        PlantDetail plantDetail=plantService.queryPlantByName(plantName);

        if(plantDetail!=null){
            int shibieCount = plantDetail.getShibieCount();
            //修改数据库 每调用一次 数据库shibieCount 加1
            plantService.updateShiBieCountPlant(plantDetail.getPlant_name(),shibieCount);
            jsonObject.put("data",shibieCount+1);
        }
        //没有的话 修改
        return jsonObject;
    }



    /**
     * jxy
     * @param
     * @return
     * 返回植物病虫害的 植物名 跟 识别次数 默认为零 不统计
     */
    @GetMapping("/countNameCiShu")
    public Object countNameCiShu(){
        JSONObject jsonObject=new JSONObject();
        List<PlantDetail> CountNameCishu=plantService.queryCountNameCiShu();

        Map<String,Integer> map = new HashMap<String,Integer>();

        for(int i=0;i<CountNameCishu.size();i++){
            String plant_name = CountNameCishu.get(i).getPlant_name();
            int shibieCount = CountNameCishu.get(i).getShibieCount();

            map.put(plant_name,shibieCount);
        }

        jsonObject.put("code",200);
        jsonObject.put("data",map);
        return jsonObject;
    }



    @GetMapping(value = "/findPlantType1")
    public Object findPlantType(String genus) {
        JSONObject jsonObject=new JSONObject();
        if(endangeredPlantService.findPlantType(genus)!=null){
//            plantFamilyTypeTable.setGenus(genus);
            jsonObject.put("code",200);
            jsonObject.put("data",endangeredPlantService.findPlantType(genus));
        }
        else{
            jsonObject.put("code",201);

        }
        return jsonObject;
    }

    @GetMapping(value = "/getAllFamilyType")
    public Object getAllType() {
        JSONObject jsonObject=new JSONObject();
        List<PlantNumberTable> list = endangeredPlantService.findAllGenus();
        PlantFamilyTypeTable plantFamilyTypeTable =new PlantFamilyTypeTable();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getPlant_family().length()!=0){
                plantFamilyTypeTable.setFamily(list.get(i).getPlant_family());
                plantFamilyTypeTable.setGenus(list.get(i).getPlant_genus());
                endangeredPlantService.insertPlantsFamily(plantFamilyTypeTable);
            }
        }
        jsonObject.put("code",200);
        return jsonObject;
    }
//    @GetMapping(value = "/getAllGenusType")
//    public Object getAllGenusType() {
//        JSONObject jsonObject=new JSONObject();
//        List<PlantNumberTable> list = endangeredPlantService.findAllGenus();
//        PlantFamilyTypeTable plantGenusTypeTable =new PlantFamilyTypeTable();
//        for(int i=0;i<list.size();i++){
//            if(list.get(i).getPlant_genus().length()!=0){
//
//            }
//        }
//        jsonObject.put("code",200);
//        return jsonObject;
//    }
    /**
     * 获取植物科与数量
     * @param page 分页
     */
    @GetMapping(value = "/getFamily")
    public BaseEntity<List> getFamily(@RequestParam(value = "page") int page) {
        BaseEntity<List> entity = new BaseEntity<>();
        entity.setCode(200);
        entity.setMessage("success");
        List list = plantImpl.getPlantFamily(page);
        if (list.size() != 0) {
            entity.setData(list);
        }
        return entity;
    }
    @GetMapping(value = "/getEndangeredPlantList")
    public Object getListByType() {
        JSONObject jsonObject=new JSONObject();
        List list = endangeredPlantService.findListByType();
        jsonObject.put("code",200);
        jsonObject.put("success","成功获取濒危植物分类列表");
        jsonObject.put("data",list);
        return jsonObject;
    }
    @GetMapping(value = "/findEndangeredPlantsListByType")
    public Object findEndangeredPlantsListByType(@RequestParam("type")String type) {
        JSONObject jsonObject=new JSONObject();
        List<EndangeredPlantsTable> list = endangeredPlantService.findEndangeredPlantsListByType(type);
        jsonObject.put("code",200);
        jsonObject.put("success","成功获取濒危植物列表");
        jsonObject.put("data",list);
        return jsonObject;
    }

    @GetMapping(value = "/findEndangeredPlantsById")
    public Object findEndangeredPlantsById(@RequestParam("id")int id) {
        JSONObject jsonObject=new JSONObject();
        EndangeredPlantsTable data = endangeredPlantService.findEndangeredPlantsById(id);
        jsonObject.put("code",200);
        jsonObject.put("success","成功获取濒危植物详情");
        jsonObject.put("data",data);
        return jsonObject;
    }
    @GetMapping(value = "/findByPlantNumber")
    public Object findByPlantNumber(@RequestParam("plant_number")String plant_number) {
        JSONObject jsonObject=new JSONObject();
        PlantNumberTable data = endangeredPlantService.findByPlantNumber(plant_number);
        if(data!=null){
            jsonObject.put("code",200);
            jsonObject.put("success","成功根据植物编号获取植物id");
            jsonObject.put("data",data);
        }else{
            jsonObject.put("code",400);
            jsonObject.put("success","无法根据编号找到植物！");
        }

        return jsonObject;
    }
    @GetMapping(value = "/updatePlantNumber")
    public Object updatePlantNumber() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("data","success");
        return jsonObject;
    }
    @PostMapping(value = "/changeAll")
    public Object changeAll(){
        JSONObject jsonObject = new JSONObject();
        EndangeredPlantsTable endangeredPlantsTable=new EndangeredPlantsTable();
        //根据用户id与问题id获取
        List<PlantTable> list = plantRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getPlantGenus().length()!=0){
                String str =String.format("%03d",endangeredPlantService.findPlantType(list.get(i).getPlantGenus()).getId());
                String str1 =String.format("%05d",list.get(i).getId());
                Integer a =endangeredPlantService.findPlantType(list.get(i).getPlantGenus()).getId()+list.get(i).getId();
                endangeredPlantService.updatePlantNumber(list.get(i).getId(),str+str1);
                jsonObject.put("code",200);
            }

        }
        return jsonObject;
    }

    /**
     * 根据科获取植物属与数量
     * @param family 科
     * @param page 分页
     */
    @GetMapping(value = "/getGenus")
    public BaseEntity<List> getGenus(@RequestParam(value = "family") String family, @RequestParam(value = "page") int page) {
        BaseEntity<List> entity = new BaseEntity<>();
        entity.setCode(200);
        entity.setMessage("success");
        List list = plantImpl.getPlantGenus(family, page);
        if (list.size() != 0) {
            entity.setData(list);
        }
        return entity;
    }

    /**
     * 根据属获取植物列表
     * @param genus 属
     * @param page 分页
     */
    @GetMapping(value = "/getPlantsByGenus")
    public BaseEntity<List<PlantDescription>> getPlantsByGenus(@RequestParam(value = "genus") String genus, @RequestParam(value = "page") int page) {
        BaseEntity<List<PlantDescription>> entity = new BaseEntity<>();
        entity.setCode(200);
        entity.setMessage("success");
        List<PlantDescription> list = plantImpl.getPlantsByGenus(genus, page);
        if (list.size() != 0) {
            entity.setData(list);
        }
        return entity;
    }

    /**
     * 根据id获取植物详情
     * @param id 植物id
     */
    @GetMapping(value = "/getPlantById")
    public BaseEntity<Plant.ResultBean> getPlantById(@RequestParam(value = "id") int id, HttpServletResponse response) throws  Exception{
        BaseEntity<Plant.ResultBean> entity = new BaseEntity<>();
        entity.setCode(200);
        entity.setMessage("success");
        entity.setData(plantImpl.getPlantById(id));
//        String content = entity.getData().getNameStd();
//        String imgPath = "";
////        String content = "http://39.105.50.119:8080/getPlantById?id="+id;
//        System.out.println(content);
//        Boolean needCompress = true;
//        //通过调用我们的写的工具类，拿到图片流
//        ByteArrayOutputStream out = QRCodeUtil.encodeIO(content, imgPath, needCompress);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("image/jpeg;charset=UTF-8");
//        response.setContentLength(out.size());
//        ServletOutputStream outputStream = response.getOutputStream();
//        entity.getData().setQr(outputStream.toString());
////        String filePath="/root/apache-tomcat-8.5.38/webapps/images/qr"+entity.getData().getNameStd()+".png";
////        服务器地址
//        String filePath="e:/qr/"+entity.getData().getNameStd()+".png";
//        File file=new File(filePath);
//        PlantTable plantTable= new PlantTable();
//        plantTable.setPlantName(entity.getData().getNameStd());
//        plantTable.setQr(filePath);
//        FileOutputStream fos=new FileOutputStream(file);
//        String fileName=plant.getPlantName()+".png";
//        FileUtil.getFileByBytes(out.toByteArray(), filePath, fileName);
//        fos.write(out.toByteArray(),0,out.toByteArray().length);
//        fos.flush();
//        fos.close();
//        outputStream.write(out.toByteArray());
//        outputStream.flush();
//        outputStream.close();
        return entity;
    }
    /**
     * 根据id获取植物详情
     */
    @GetMapping(value = "/getPlantByName")
    public BaseEntity<Plant.ResultBean> getPlantById(@RequestParam(value = "name") String name) throws  Exception{
        BaseEntity<Plant.ResultBean> entity = new BaseEntity<>();
        entity.setCode(200);
        entity.setMessage("success");
        entity.setData(plantImpl.getPlantByName(name));
        return entity;
    }
    /**
     * 根据植物编号获取植物详情
     */
    @GetMapping(value = "/getPlantBybianhao")
    public Object getPlantBybianhao(@RequestParam("devision")String devision){
        JSONObject jsonObject=new JSONObject();
        List<PlantDetail> list = plantService.getPlantBybianhao(devision);
        jsonObject.put("code",200);
        jsonObject.put("success","成功根据植物编号获取植物");
        jsonObject.put("data",list);
        return jsonObject;
    }
    @PostMapping(value = "/recommendPlantByKeywords")
    public Object recommendPlantByKeywords(String keyword) throws Exception{
        JSONObject jsonObject=new JSONObject();
        if(keyword.length()!=0){
            List<SolrPlant> list=plantSolrService.recommendPlantByKeywords(keyword);
//        System.out.println(list.size());
//        for(int i=0;i<list.size();i++){
//            result.get(i).setNameStd(list.get(i).getPlant_name());
//            result.get(i).setNameLt(list.get(i).getPlant_latin_name());
//            result.get(i).setAlias(list.get(i).getPlant_alias());
//            result.get(i).setDescription(list.get(i).getPlant_description());
//            result.get(i).setFamilyCn(list.get(i).getPlant_family());
//            result.get(i).setGenusCn(list.get(i).getPlant_genus());
//            List<String> images = Arrays.asList(list.get(i).getPlant_image().split("#"));
//            result.get(i).setImages(images);
//            infoBean.get(i).setBxtz(list.get(i).getPlant_bxtz());
//            infoBean.get(i).setFbdq(list.get(i).getPlant_fbdq());
//            infoBean.get(i).setHksj(list.get(i).getPlant_hksj());
//            infoBean.get(i).setJzgy(list.get(i).getPlant_jzgy());
//            infoBean.get(i).setXgsc(list.get(i).getPlant_xgsc());
//            infoBean.get(i).setYhjs(list.get(i).getPlant_yhjs());
//            result.get(i).setInfo(infoBean.get(i));
//        }
            jsonObject.put("code", "200");
            jsonObject.put("message", "找到相关植物");
            jsonObject.put("data", list);
        }

        return jsonObject;
    }
    @PostMapping(value = "/recommendSupplierByKeywords")
    public Object recommendSupplier(String keyword) throws Exception{
        JSONObject jsonObject=new JSONObject();
        if(keyword!=null){
            List<Supplier> list=plantSolrService.recommendSupplier(keyword);
            jsonObject.put("code", "200");
            jsonObject.put("message", "找到供应商");
            jsonObject.put("data", list);
        }return jsonObject;
    }
    @PostMapping(value = "/findSupplierById")
    public Object findSupplierById(int supplierid) throws Exception{
        JSONObject jsonObject=new JSONObject();

        List<Supplier> list=plantSolrService.findSupplierById(supplierid);
        jsonObject.put("code", "200");
        jsonObject.put("message", "找到供应商");
        jsonObject.put("data", list.get(0));
        return jsonObject;
    }
    @PostMapping(value = "/searchQuestion")
    public Object searchQuestion(String keyword) throws Exception{
        JSONObject jsonObject=new JSONObject();
        if(keyword.length()!=0){
            List<Question> list1= new ArrayList<Question>();
            List<Question> list=plantSolrService.searchQuestion(keyword);
            for(int i=0;i<list.size();i++){
                if(list.get(i).getQtype()==0)
                list1.add(list.get(i));
            }
            jsonObject.put("code", "200");
            jsonObject.put("message", "找到相关问题");
            jsonObject.put("data", list1);
        }
        return jsonObject;
    }


    @PostMapping(value = "/searchExpert")
    public Object searchExpert(String keyword) throws Exception{
        JSONObject jsonObject=new JSONObject();
        if(keyword.length()!=0){
            List<Experts> list1= new ArrayList<Experts>();
            List<ExpertSolr> list=plantSolrService.searchExpert(keyword);
            for(int i=0;i<list.size();i++){
               list1.add(expertsService.getExpertsById(list.get(i).getExpertsid()));
            }
            jsonObject.put("code", "200");
            jsonObject.put("message", "找到供应商");
            jsonObject.put("data", list1);
        }return jsonObject;
    }

    /**
     * 根据病虫查询不同地点的数据
     * @param names
     * @return
     */
    @PostMapping("/queryDidianbingchong")
    public Object queryDidianbingchong(String names[]){
        SGPCResult result =new SGPCResult();
        List<kvname> list = new ArrayList<>();
        String [] nameDiDian=names; //默认的为10个  石景山 朝阳 东城 西城 延庆 昌平 海淀 通州 房山 怀柔
        for(int i=0;i<nameDiDian.length;i++){
            kvname k = new kvname();
            int count=plantService.queryDidianByName(nameDiDian[i]);
             k.setName(nameDiDian[i]);
             k.setValue(count);
             list.add(k);
       }

        return result.ok(list);
   }

    /**
     * 根据时间查询最新的病虫前四数据
     * @return
     */
   @PostMapping("/queryQianSiBingChong")
    public Object queryQianSiBingChong(){
       JSONObject jsonObject=new JSONObject();
       List<addDiseaseCount> addDiseaseCountsList=plantService.queryQianSi();
       //将时间字符串转成通用的
       jsonObject.put("data",addDiseaseCountsList);
       jsonObject.put("msg","返回数据成功");
       jsonObject.put("code",200);
       return jsonObject;
   }

    /**
     * 根据植物查询不同地点的数据
     * @param names
     * @return
     */
    @PostMapping("/queryDidianzhiwu")
    public Object queryDidianzhiwu(String names[]){
        SGPCResult result =new SGPCResult();
        List<kvname> list = new ArrayList<>();
        String [] nameDiDian=names; //默认的为10个  石景山 朝阳 东城 西城 延庆 昌平 海淀 通州 房山 怀柔
        for(int i=0;i<nameDiDian.length;i++){
            kvname k = new kvname();
            int count=plantService.querydidianByNamePlant(nameDiDian[i]);
            k.setName(nameDiDian[i]);
            k.setValue(count);
            list.add(k);
        }
        return result.ok(list);
    }

    /**
     * 根据时间查询最新的植物前四数据
     * @return
     */
    @PostMapping("/queryQianSiZhiwu")
    public Object queryQianSiZhiwu(){
        JSONObject jsonObject=new JSONObject();
        List<addPlantCount> addDiseaseCountsList=plantService.queryQianSiByPlant();
        //将时间字符串转成通用的
        jsonObject.put("data",addDiseaseCountsList);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 返回历史识别次数
     * @return
     */
    @PostMapping("/countLiShiShiBie")
    public Object countLiShiShiBie(){
        JSONObject jsonObject=new JSONObject();
        int countZhiWu=plantService.countLiShiShiBieByZhiWu();
        int countBiChong=plantService.countLiShiShiBieByBingChong();
        int zongshu=countBiChong+countZhiWu;
        jsonObject.put("data",zongshu);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 返回今日识别次数
     */
    @PostMapping("/countLiShiShiBieToday")
    public Object countLiShiShiBieToday(){
        JSONObject jsonObject=new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = sdf.format(date);
        int countZhiWu=plantService.countLiShiShiBieByZhiWuToday(today);
        int countBiChong=plantService.countLiShiShiBieByBingChongToday(today);
        int zongshu=countBiChong+countZhiWu;
        jsonObject.put("data",zongshu);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 返回参与识别总人数
     */
    @PostMapping("/countUser")
    public Object countUser(){
        JSONObject jsonObject=new JSONObject();
        int count = plantService.countUser();
        jsonObject.put("data",count);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 随机 查询四个专家
     */
    @PostMapping("/queryExpertRound")
    public Object queryExpertRound(){
        JSONObject jsonObject=new JSONObject();
        List<Experts> listExperts = plantService.queryExpertRound();
        jsonObject.put("data",listExperts);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 返回 各种类型的百分比
     * 1
     */
    @PostMapping("/queryTypePercent")
    public Object queryTypePercent(){
        JSONObject jsonObject=new JSONObject();
        int countZhiWu=plantService.countLiShiShiBieByZhiWu();
        //type1
        double typeCount1 =plantService.queryLiShiShiBieByZhiWuByType1();
        double typeCount2 =plantService.queryLiShiShiBieByZhiWuByType2();

        double type1Percent = (typeCount1/countZhiWu)*100;
        double type2Percent = (typeCount2/countZhiWu)*100;
        double type3Percent = 100-type1Percent-type2Percent;
        List<Double>list = new ArrayList<>();
        list.add(type1Percent);
        list.add(type2Percent);
        list.add(type3Percent);

        jsonObject.put("data",list);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 10秒内查询addDieaseCount数据
     * 查询几秒内
     */
    @PostMapping("/queryDieaseCountByTime")
    public Object queryDieaseCountByTime(){
        JSONObject jsonObject=new JSONObject();
        //输入不同的秒数 看能不能有新的数据
        List<addDiseaseCount> list = plantService.queryDiseaseCountByTime();
        jsonObject.put("data",list);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 10秒内查询addDieaseCount数据
     * 查询几秒内
     */
    @PostMapping("/queryPlantCountByTime")
    public Object queryPlantCountByTime(){
        JSONObject jsonObject=new JSONObject();
        //输入不同的秒数 看能不能有新的数据
        List<addPlantCount> list = plantService.queryPlantCountByTime();
        jsonObject.put("data",list);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 1天内查询addDieaseCount数据
     *
     */
    @PostMapping("/queryDieaseCountByTimeOneDay")
    public Object queryDieaseCountByTimeOneDay(){
        JSONObject jsonObject=new JSONObject();
        //输入不同的秒数 看能不能有新的数据
        List<addDiseaseCount> list = plantService.queryDiseaseCountByTimeOneDay();
        jsonObject.put("data",list);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 1天内查询addDieaseCount数据
     *
     */
    @PostMapping("/queryPlantCountByTimeOneDay")
    public Object queryPlantCountByTimeOneDay(){
        JSONObject jsonObject=new JSONObject();
        //输入不同的秒数 看能不能有新的数据
        List<addPlantCount> list = plantService.queryPlantCountByTimeOneDay();
        jsonObject.put("data",list);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    /**
     * 功能描述:植物推荐
     * @Param: [username]
     * @Return: java.lang.Object
     * @Author: aq
     * @Date: 2019/12/25 10:54
     */
    @PostMapping(value = "/getHistoryByUsername")
    public Object getHistoryByUsername(String username) throws Exception{
        JSONObject jsonObject=new JSONObject();

        List<historyPlant> list=plantService.getHistoryByUsername();
        List<historyPlant> list2=plantService.getUser();

        UserSet userSet=new UserSet();
        UserSet.SUser user=null;
        for(int i=0;i<list2.size();i++){
            user=userSet.put(list2.get(i).getUsername());
            for(int j=0;j<list.size();j++){
                System.out.println(list2.get(i).getUsername()+"------"+list.get(j).getUsername());
                if(list2.get(i).getUsername().equals(list.get(j).getUsername())) {
                    user.set(list.get(j).getPlantname(),list.get(j).getHit());
                }
            }
            user.create();
        }
        Recommend recommend = new Recommend();
        List<UserSet.Set> recommendations = recommend.recommend(username, userSet);
        System.out.println("-----------------------");
        List<String> res=new ArrayList<>();
        List<PlantDetail> result=new ArrayList<>();
        for (UserSet.Set set : recommendations) {
            res.add(set.username);
            result.add(plantService.getPlantByZhiwuming(set.username));

        }
        jsonObject.put("code", "200");
        jsonObject.put("message", "找到推荐植物");
        jsonObject.put("data", result);
        return jsonObject;
    }
    /**
     * 功能描述:
     * @Param:
     * @Return:
     * @Author: aq
     * @Date: 2019/12/25 10:59
     */
    /**
     * 根据植物名获取植物详情
     */
    @GetMapping(value = "/getPlantByZhiwuming")
    public Object getPlantByZhiwuming(@RequestParam("plant_name")String plant_name){
        JSONObject jsonObject=new JSONObject();
        PlantDetail list = plantService.getPlantByZhiwuming(plant_name);
        jsonObject.put("code",200);
        jsonObject.put("success","成功根据植物名获取植物");
        jsonObject.put("data",list);
        return jsonObject;
    }
    @PostMapping("/CBrecommendPlant")
    public Object recommendPlant(int id){
//        int id = 4;
        List<Map.Entry<Integer,Double>> v = plantService.recommendPlant(id);
        List<PlantTuijian> list=new ArrayList<>();
//        for (int i = 0; i <v.size() ; i++) {
        for (int i = 0; i <5; i++) {
            list.add(plantService.getRecipeById(v.get(i).getKey()));
        }
        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("data",v);
        jsonObject.put("data",list);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }
}
