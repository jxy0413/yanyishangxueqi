package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.dao.AlertRepository;
import cn.edu.bjfu.igarden.entity.Alert;
import cn.edu.bjfu.igarden.service.PlantService;
import cn.edu.bjfu.igarden.util.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by yxy on 2019/5/7.
 */
@RestController
public class AlertController {
    private static final String BASE_URL = "http://39.105.50.119:8080/alert/";
    @Value("${web.alertImagePath}")
    private String path;
    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private PlantService plantService;

    @PostMapping(value = "/addAlert")
    public Object addAlert(Alert alert){
        String imagePath = "";
        if (alert.getImgs().equals("1")){
            alert.setImgs(null);
            System.out.println(alert.toString());
        }
       else if(!alert.getImgs().equals("1")){
            String alertImage=alert.getImgs();
            String base64Str[]= alertImage.split("#");

            for(int i=0;i<base64Str.length;i++){
                StringBuffer fileName = new StringBuffer();
                fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
                if(base64Str[i].contains("data:image/jpeg;base64,")){
                    base64Str[i] = base64Str[i].replace("data:image/jpeg;base64,", "").replaceAll("[\\s*\t\n\r]","+").replaceAll(",","");
                }
                else if(base64Str[i].contains("data:image/png;base64,")){
                    base64Str[i] = base64Str[i].replace("data:image/png;base64,", "").replaceAll("[\\s*\t\n\r]","+").replaceAll(",","");
                }else {
                    base64Str[i] = base64Str[i].replace("data:image/jpg;base64,", "").replaceAll("[\\s*\t\n\r]", "+").replaceAll(",", "");
                }
                imagePath =imagePath+BASE_URL + fileName.append(".jpg").toString()+"#";
                System.out.println("imagePath="+imagePath);
                File file = new File(path, fileName.toString());
                byte[] fileBytes = Base64.getDecoder().decode(base64Str[i]);
                try {
                    FileUtil.writeByteArrayToFile(file, fileBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            alert.setImgs(imagePath);
            System.out.println("imagePath="+imagePath);
        }
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("code",200);
        jsonObject.put("message","success");
        jsonObject.put("data", alertRepository.save(alert));
        return jsonObject;
    }
    @PostMapping(value = "/selectAlert")
    public Object selectAlert() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("message","success");
        jsonObject.put("data", alertRepository.findAll());
        return jsonObject;
    }

    /**
     * jxy
     * @param id
     * @return 根据id 将0改成1 未解决改成解决
     */
    @PostMapping("/updateAlert")
    public Object updateAlert(int id){
        JSONObject jsonObject=new JSONObject();
        //修改
        int count=plantService.updateAlert(id);
        if(count==1){
            jsonObject.put("code",200);
            jsonObject.put("message","success");
        }else{
        jsonObject.put("code",201);
        jsonObject.put("message","fail");
    }
        return  jsonObject;
    }

    /**
     * 不小心 点错了 再将1改成0 从已解决变成未解决
     * @param id
     * @return
     */
    public Object updateAlertRevoke(int id){
        JSONObject jsonObject=new JSONObject();
        //修改
        int count=plantService.updateAlertRevoke(id);
        if(count==1){
            jsonObject.put("code",200);
            jsonObject.put("message","success");
        }else{
            jsonObject.put("code",201);
            jsonObject.put("message","fail");
        }
        return  jsonObject;
    }




}
