package cn.edu.bjfu.igarden.controller;


import cn.edu.bjfu.igarden.dao.ShibieRepository;
import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.service.DiseaseclassifyService;
import cn.edu.bjfu.igarden.service.DiseaseclassifyresultService;
import cn.edu.bjfu.igarden.service.PlantService;
import cn.edu.bjfu.igarden.service.UserService;
import cn.edu.bjfu.igarden.util.FileUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yxy on 2019/2/28.
 */
@RestController
public class ImageClassifyController {


    private static final String BASE_URL = "http://39.105.50.119:8080/shibie/";
    @Value("${web.shibieImagePath}")
    private String path;

//    private String path="E:\\imgs/";

    public static final String APP_ID = "15438248";
    public static final String API_KEY = "SxKBGdBZT1jZlUHTzsTzQeEi";
    public static final String SECRET_KEY = "1mQkz5E7oZY78lH1GjmvOK9gvc6i8VxX";

    public DiseaseclassifyresultService diseaseclassifyresultService;
    public DiseaseclassifyService diseaseclassifyService;
    public UserService userService;
    @Autowired
    ShibieRepository shibieRepository;

    @Autowired
    PlantService plantService;

    @Autowired
    public ImageClassifyController(DiseaseclassifyresultService diseaseclassifyresultService,
                                   DiseaseclassifyService diseaseclassifyService,
                                   UserService userService){
        this.diseaseclassifyresultService=diseaseclassifyresultService;
        this.diseaseclassifyService = diseaseclassifyService;
        this.userService = userService;
    }

    @PostMapping(value = "/imageAnalysis")
    public Object imageAnalysis( ServletRequest request,ImgClassify imgClassify) {
        String imaged = "";
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if (token == null) {
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        } else {
            Login loginInfo = userService.findUseridByToken(token);
            if (loginInfo == null) {
                jsonObject.put("code", "404");
                jsonObject.put("message", "找不到登录信息");
            } else {
                User user = userService.findById(loginInfo.getUserid());
                if (user != null) {
                    AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
                    client.setConnectionTimeoutInMillis(2000);
                    client.setSocketTimeoutInMillis(60000);
                    HashMap<String, String> options = new HashMap<String, String>();
                    options.put("top_num", "5");
                    options.put("baike_num", "5");

                    String image = "src/main/resources/static/茶翅蝽.jpg";
                    String imagePath = "";
                    if (imgClassify.getImage().length() != 0 || !imgClassify.getImage().equals("")) {
                        String questionImage = imgClassify.getImage();
                        String base64Str[] = questionImage.split("#");
                        System.out.println(base64Str[0]);
                        for (int i = 0; i < base64Str.length; i++) {
                            StringBuffer fileName = new StringBuffer();
                            fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
                            if (base64Str[i].contains("data:image/jpeg;base64,")) {
                                base64Str[i] = base64Str[i].replace("data:image/jpeg;base64,", "").replaceAll("[\\s*\t\n\r]", "+").replaceAll(",", "");
                            } else if (base64Str[i].contains("data:image/png;base64,")) {
                                base64Str[i] = base64Str[i].replace("data:image/png;base64,", "").replaceAll("[\\s*\t\n\r]", "+").replaceAll(",", "");
                            } else {
                                base64Str[i] = base64Str[i].replace("data:image/jpg;base64,", "").replaceAll("[\\s*\t\n\r]", "+").replaceAll(",", "");
                            }
                            System.out.println("[base64:]" + base64Str[0]);
                            imagePath = imagePath + BASE_URL + fileName.append(".jpg").toString() + "#";
                            System.out.println("imagePath=" + imagePath);
                            File file = new File(path, fileName.toString());
                            byte[] fileBytes = Base64.getDecoder().decode(base64Str[i]);
                            byte[] a = Base64Util.decode(base64Str[i]);
                            try {
                                FileUtil.writeByteArrayToFile(file, a);
                                System.out.println("aaaaaaaa");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        imgClassify.setImage(imagePath);

                    }
                    imagePath = imagePath.substring(0, imagePath.length() - 1).replace(BASE_URL, path);
                    System.out.println("imagePath=" + imagePath);

                    JSONObject jsonimg = JSONObject.parseObject(client.animalDetect(imagePath, options).toString());
                    System.out.println("识别结果" + jsonimg);
                    if(jsonimg.get("error_msg")==null){
                        JSONArray jsonArray1 = JSONArray.parseArray(jsonimg.get("result").toString());
                        System.out.println(jsonArray1.get(0).toString());

                        System.out.println(jsonArray1.getJSONObject(0).get("name").equals("非动物"));

                        if (!jsonArray1.getJSONObject(0).get("name").equals("非动物")){
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            System.out.println("11111111111111111111111111111111111111111");
                            imgClassify.setUserid(user.getId());
                            imgClassify.setImage(imagePath);
//                    diseaseclassifyService.addDiseaseClassifyResult(imgClassify);
                            imgClassify.setCreatetime(dateFormat.format(new Date()));
                            ImgClassify shibie = shibieRepository.save(imgClassify);

                            System.out.println("识别结果" + jsonimg);
                            JSONArray jsonArray = JSONArray.parseArray(jsonimg.get("result").toString());
                            List<ImgClassifyResult> imgClassifyResultList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JSONObject job = jsonArray.getJSONObject(i);
                                ImgClassifyResult imgClassifyResult = new ImgClassifyResult();

                                JSONObject baike = JSONObject.parseObject(job.get("baike_info").toString());
                                imgClassifyResult.setScore(String.format("%.2f", Double.parseDouble(job.get("score").toString()) * 100) + "%");
                                imgClassifyResult.setBname(job.get("name").toString());
                                imgClassifyResult.setBaike_url(baike.get("baike_url").toString());
                                imgClassifyResult.setImage_url(baike.get("image_url").toString());
                                imgClassifyResult.setDescription(baike.get("description").toString());
                                imgClassifyResult.setShibieid(shibie.getId());
                                diseaseclassifyresultService.addDiseaseClassifyResult(imgClassifyResult);
                                imgClassifyResultList.add(imgClassifyResult);
                            }

                            jsonObject.put("code", 200);
                            jsonObject.put("message", "新增一条识图记录,返回识别结果");
                            jsonObject.put("data", imgClassifyResultList);
                        }else{
                            jsonObject.put("code", 401);
                            jsonObject.put("message", "暂无识别结果");
                        }
                    }else{
                        jsonObject.put("code", 400);
                        jsonObject.put("message", "无信息");
                    }
                }
            }
        }
        return jsonObject;
    }

    /**
     *
     * @param
     * @param type  1代表 拍照 2代表 推理
     * @return
     * jxy
     */
    @PostMapping("/addDiseaseCount")
    public Object addDiseaseCount(String diseaseName,int type,String address,String location){
            JSONObject jsonObject = new JSONObject();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String diseaseTime = df.format(new Date());
        //表示拍照
            //存入name 如果没有Name 存入name  有的话次数加1
              int count=userService.addDiseaseCount(type,diseaseName,diseaseTime,address,location);
              if(count==1){
                  jsonObject.put("code",200);
                  jsonObject.put("success","拍照存入成功");
              }else{
                  jsonObject.put("code",201);
                  jsonObject.put("fail","拍照存入失敗");
              }
        return jsonObject;
    }

    /**
     *
     * @param
     * @param type  1代表 拍照 2代表 二维码 3代表 编号查询
     * @return
     * jxy
     */
    @PostMapping("/addPlantCount")
    public Object addPlantCount(String plantName,int type,String address,String location){
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String diseaseTime = df.format(new Date());
        //表示拍照
        //存入name 如果没有Name 存入name  有的话次数加1
        int count=userService.addPlantCount(type,plantName,diseaseTime,address,location);
        if(count==1){
            jsonObject.put("code",200);
            jsonObject.put("success","存入成功");
        }else{
            jsonObject.put("code",201);
            jsonObject.put("fail","存入失敗");
        }
        return jsonObject;
    }



    @PostMapping(value = "/addShibie")
    public Object addShibie(ServletRequest request, ImgClassify imgClassify){
        String imaged="";
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest)request;
        String token = req.getHeader("token");
        if(token == null){
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        }else {
            Login loginInfo = userService.findUseridByToken(token);
            if (loginInfo == null) {
                jsonObject.put("code", "404");
                jsonObject.put("message", "找不到登录信息");
            } else {
                User user = userService.findById(loginInfo.getUserid());
                if (user != null) {

                    String imagePath = "";
                    if(!imgClassify.getImage().equals("")){
                        imaged=imgClassify.getImage();
//            String questionImage=answer.getImage();
//            String base64Str[]= ;
//            System.out.println(base64Str[0]);
//            for(int i=0;i<base64Str.length;i++){
                        StringBuffer fileName = new StringBuffer();
                        fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
                        if(imaged.contains("data:image/jpeg;base64,")){
                            imaged = imaged.replace("data:image/jpeg;base64,", "").replaceAll("[\\s*\t\n\r]","+").replaceAll(",","");
                        }
                        else if(imaged.contains("data:image/png;base64,")){
                            imaged = imaged.replace("data:image/png;base64,", "").replaceAll("[\\s*\t\n\r]","+").replaceAll(",","");
                        }else {
                            imaged = imaged.replace("data:image/jpg;base64,", "").replaceAll("[\\s*\t\n\r]", "+").replaceAll(",", "");
                        }
                        imagePath =imagePath+BASE_URL + fileName.append(".jpeg").toString();
                        System.out.println("imagePath1="+imagePath);
                        File file = new File(path, fileName.toString());
                        byte[] fileBytes = Base64.getDecoder().decode(imaged);
                        byte[] a= Base64Util.decode(imaged);
                        try {
                            FileUtil.writeByteArrayToFile(file, a);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("imagepath2="+imaged);

                    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    imgClassify.setUserid(user.getId());
                    imgClassify.setImage(imagePath);
//                    diseaseclassifyService.addDiseaseClassifyResult(imgClassify);
                    imgClassify.setCreatetime(dateFormat.format(new Date()));
                    ImgClassify shibie= shibieRepository.save(imgClassify);


                    AipImageClassify client = new AipImageClassify(APP_ID,API_KEY,SECRET_KEY);
                    client.setConnectionTimeoutInMillis(2000);
                    client.setSocketTimeoutInMillis(60000);
                    HashMap<String,String>options = new HashMap<String, String>();
                    options.put("top_num","2");
                    options.put("baike_num","2");

//                    String image = "src/main/resources/static/mapichun.jpg";
                    String image="/Users/yxy/a/15727b0ba40043768daead462d3d6937.jpeg";
                    JSONObject jsonimg=JSONObject.parseObject(client.animalDetect(image,options).toString());
                    System.out.println("识别结果"+jsonimg);
                    JSONArray jsonArray=JSONArray.parseArray(jsonimg.get("result").toString());
                    List<ImgClassifyResult> imgClassifyResultList=new ArrayList<>();
                    for(int i=0;i<jsonArray.size();i++){
                        JSONObject job=jsonArray.getJSONObject(i);
                        ImgClassifyResult imgClassifyResult=new ImgClassifyResult();

                        JSONObject baike=JSONObject.parseObject(job.get("baike_info").toString());
                        imgClassifyResult.setScore(String.format("%.2f",Double.parseDouble(job.get("score").toString())*100)+"%");
                        imgClassifyResult.setBname(job.get("name").toString());
                        imgClassifyResult.setBaike_url(baike.get("baike_url").toString());
                        imgClassifyResult.setImage_url(baike.get("image_url").toString());
                        imgClassifyResult.setDescription(baike.get("description").toString());
                        imgClassifyResult.setShibieid(shibie.getId());
                        diseaseclassifyresultService.addDiseaseClassifyResult(imgClassifyResult);
                        imgClassifyResultList.add(imgClassifyResult);
                    }

                    jsonObject.put("code",200);
                    jsonObject.put("message","新增一条识图记录,返回识别结果");
                    jsonObject.put("data",imgClassifyResultList);
                }
            }
        }
        return  jsonObject;

    }

    @PostMapping(value = "/imageAnalysis1")
    public Object imageAnalysis1() {
        AipImageClassify client = new AipImageClassify(APP_ID,API_KEY,SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

//        System.out.println("得到的图片是:"+image);
        HashMap<String,String>options = new HashMap<String, String>();
        options.put("top_num","3");
        options.put("baike_num","3");


        String image = "src/main/resources/static/蝼蛄.jpg";
        Object object = client.animalDetect(image,options);

        System.out.println(object);
//        jsonObject.
//        BaseEntity baseEntity = new BaseEntity();
//        baseEntity.setCode(200);
//        baseEntity.setMessage("success");
//        baseEntity.setData((client.animalDetect(image,options)));
//
        return  object;

    }


}
