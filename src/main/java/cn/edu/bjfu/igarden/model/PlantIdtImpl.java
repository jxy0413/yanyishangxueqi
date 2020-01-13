package cn.edu.bjfu.igarden.model;

import cn.edu.bjfu.igarden.controller.SignController;
import cn.edu.bjfu.igarden.dao.PlantRepository;
import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.service.EndangeredPlantService;
import cn.edu.bjfu.igarden.util.LogUtil;
import cn.edu.bjfu.igarden.util.QRCodeUtil;
import java.io.ByteArrayOutputStream;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class PlantIdtImpl {
    @Autowired
    private SignController signController;
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private EndangeredPlantService endangeredPlantService;

    public BaseEntity<List<PlantList.ResultBean>> uploadImgBase64(String img) {
        img = img.replaceAll(" ", "+");
        PlantList plant = signController.getFlowerList(img);
        BaseEntity<List<PlantList.ResultBean>> entity = new BaseEntity<>();
        entity.setCode(plant.getStatus() == 0 ? 200 : 500);
        entity.setMessage(plant.getStatus() == 0 ? "success" : plant.getMessage());
        entity.setData(plant.getStatus() == 0 ? plant.getResult() : null);
        LogUtil.d("test img:" + img);
        LogUtil.d("test code:" + plant.getStatus());
        return entity;
    }

    public BaseEntity<Plant.ResultBean> getPlant(String infoUrl, HttpServletResponse response) throws Exception{
        Plant plant = signController.getFlower(infoUrl);
        BaseEntity<Plant.ResultBean> entity = new BaseEntity<>();
        entity.setCode(plant.getStatus() == 0 ? 200 : 500);
        entity.setMessage(plant.getStatus() == 0 ? "success" : plant.getMessage());
        entity.setData(plant.getStatus() == 0 ? plant.getResult() : null);
        String content = entity.getData().getNameStd();
        String family =entity.getData().getFamilyCn();
        String genus =entity.getData().getGenusCn();
        System.out.println(genus+"1211111111111111");
        String imgPath = "";
//        String content = "http://39.105.50.119:8080/getPlantById?id="+id;
        Boolean needCompress = true;
        //通过调用我们的写的工具类，拿到图片流
        ByteArrayOutputStream out = QRCodeUtil.encodeIO(content, imgPath, needCompress);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("image/jpeg;charset=UTF-8");
        response.setContentLength(out.size());
        ServletOutputStream outputStream = response.getOutputStream();
        entity.getData().setQr(outputStream.toString());
        String filePath="/root/apache-tomcat-8.5.38/webapps/images/qr/"+entity.getData().getNameStd()+".png";
        System.out.println(filePath);
//        服务器地址
//        String filePath="e:/qr/"+entity.getData().getNameStd()+".png";
        File file=new File(filePath);
        PlantTable plantTable= new PlantTable();
        PlantFamilyTypeTable plantFamilyTypeTable =new PlantFamilyTypeTable();
        // 保存植物信息
        Plant.ResultBean plantResult = plant.getResult();
        Plant.ResultBean.InfoBean plantInfo = plantResult.getInfo();
        plantTable.setPlantName(plantResult.getNameStd());
        plantTable.setPlantLatinName(plantResult.getNameLt());
        plantTable.setPlantFamily(plantResult.getFamilyCn());
        plantTable.setPlantAlias(plantResult.getAlias());
        plantTable.setPlantGenus(plantResult.getGenusCn());
        plantTable.setDevision(plantResult.getDevisionCn());
        if(endangeredPlantService.findPlantType(genus)!=null){
            String str =String.format("%03d",endangeredPlantService.findPlantType(genus).getId());
            String str1 =String.format("%05d",entity.getData().getId());
            plantTable.setPlant_number(str+str1);
        }
        else{
            plantFamilyTypeTable.setFamily(family);
            plantFamilyTypeTable.setGenus(genus);
            endangeredPlantService.insertPlantsFamily(plantFamilyTypeTable);
            String str =String.format("%03d",endangeredPlantService.findPlantType(genus).getId());
            String str1 =String.format("%05d",entity.getData().getId());
            plantTable.setPlant_number(str+str1);
        }
        plantTable.setQr(filePath);
        StringBuilder saveImages = new StringBuilder();
        List<String> images = plantResult.getImages();
        for (int i = 0; i < images.size(); i++) {
            if (i == images.size() - 1) {
                saveImages.append(images.get(i));
            } else {
                saveImages.append(images.get(i)).append("#");
            }
        }
        plantTable.setPlantImage(saveImages.toString());
        plantTable.setPlantDescription(plantResult.getDescription());
        plantTable.setPlantXgsc(plantInfo.getXgsc());
        plantTable.setPlantJzgy(plantInfo.getJzgy());
        plantTable.setPlantFbdq(plantInfo.getFbdq());
        plantTable.setPlantYhjs(plantInfo.getYhjs());
        plantTable.setPlantBxtz(plantInfo.getBxtz());
        plantTable.setPlantHksj(plantInfo.getHksj());

        plantTable.setCreateTime(System.currentTimeMillis() / 1000);
        plantTable.setUpdateTime(System.currentTimeMillis() / 1000);
        plantTable.setHits(plantTable.getHits());
        plantTable.hitsPlus();
        FileOutputStream fos=new FileOutputStream(file);
//        String fileName=plant.getPlantName()+".png";
//        FileUtil.getFileByBytes(out.toByteArray(), filePath, fileName);
        fos.write(out.toByteArray(),0,out.toByteArray().length);
        fos.flush();
        fos.close();
//        outputStream.write(out.toByteArray());
//        outputStream.flush();
//        outputStream.close();
        savePlant(plantTable);
        LogUtil.d("test:" + plant.getStatus());
        LogUtil.d("test:" + infoUrl);
        LogUtil.d("test:" + plantTable.getQr());
        LogUtil.d("test:" + plant.getResult().getDescription());
        return entity;
    }

    private void savePlant(PlantTable plant) {
        PlantTable plantTable = plantRepository.findByPlantName(plant.getPlantName());
        if (plantTable != null) {
            plant.setId(plantTable.getId());
            // 记录查询次数
            plant.setHits(plantTable.getHits());
            plant.hitsPlus();
            LogUtil.d("test: hits: " + plant.getHits());
            plant.setCreateTime(plantTable.getCreateTime());
            plant.setDeleteTime(0);
        }
        plantRepository.save(plant);
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


}
