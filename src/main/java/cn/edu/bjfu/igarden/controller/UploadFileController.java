package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.Experts;
import cn.edu.bjfu.igarden.entity.Login;
import cn.edu.bjfu.igarden.entity.Question;
import cn.edu.bjfu.igarden.entity.User;
import cn.edu.bjfu.igarden.service.UserService;
import cn.edu.bjfu.igarden.util.FileUtil;
import cn.edu.bjfu.igarden.util.UUIDUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Base64;

/**
 * @ClassName: UploadFileController
 * @Desc:  上传文件
 * swagger: http://localhost:8015/swagger-ui.html#/upload45file45controller
 * http://localhost:8015/upload
 *
 * @Author: liuhefei
 * @Date: 2019/1/12 10:59
 */
@RestController
public class UploadFileController {
    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String BASE_URL = "http://39.105.50.119:8080/";
    @Value("${web.uploadPath}")
    private String path;
    private final ResourceLoader resourceLoader;

    @Autowired
    public UploadFileController(ResourceLoader resourceLoader, UserService userService) {
        this.resourceLoader = resourceLoader;
        this.userService = userService;
    }

    @PostMapping(value = "/fileUpload")
    public JSONObject upload(@RequestParam("fileName") MultipartFile file[], ServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //要上传的目标文件存放的路径
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if (token == null) {
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        } else {
            Login login = userService.findUseridByToken(token);
            if (login == null) {
                jsonObject.put("code", "406");
                jsonObject.put("message", "查询失败");
            } else {
                String localPath = path;
                //上传成功或失败的提示信息
                if (file != null && file.length >= 1) {
                    BufferedOutputStream bw = null;
                    try {
                        for (MultipartFile files : file) {
                            User user = new User();
                            String fileName = UUIDUtils.getUUID()+files.getOriginalFilename();
                            if (fileName != null && !"".equalsIgnoreCase(fileName.trim()) && isImageFile(fileName)) {
                                if (FileUtil.upload(files, localPath, fileName)) {
                                    //上传成功，给出页面提示
                                    logger.info("文件名：file[]", fileName);
                                    String realPath = path + File.separator + fileName;  //原图地址
                                    String resultPath = path + File.separator + "thumbnail" + File.separator + fileName; //缩略图地址
                                    user.setAvatar(resultPath);
                                    user.setId(login.getUserid());
                                    userService.updateUser(user);
                                    jsonObject.put("code", "200");
                                    jsonObject.put("message", "图片上传成功");
                                    jsonObject.put("fileName", fileName);
                                } else {
                                    jsonObject.put("code", "400");
                                    jsonObject.put("massage", "文件上传失败");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        //显示图片
        return jsonObject;
    }

    @PostMapping(value = "/changeAvatar")
    public JSONObject changeAvatar(@RequestParam("fileName") MultipartFile file, ServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //要上传的目标文件存放的路径
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if (token == null) {
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        } else {
            Login login = userService.findUseridByToken(token);
            if (login == null) {
                jsonObject.put("code", "406");
                jsonObject.put("message", "查询失败");
            } else {
                String localPath = path;
                //上传成功或失败的提示信息
                User user = new User();
                String fileName = file.getOriginalFilename();
                if (fileName != null && !"".equalsIgnoreCase(fileName.trim()) && isImageFile(fileName)) {
                    if (FileUtil.upload(file, localPath, fileName)) {
                        //上传成功，给出页面提示
                        String fileNames = UUIDUtils.getUUID() + file.getOriginalFilename();
                        logger.info("文件名：file", fileNames);
                        String realPath = path + File.separator + fileNames;  //原图地址
                        String resultPath = path + File.separator + "thumbnail" + File.separator + fileNames; //缩略图地址
                        user.setAvatar(resultPath);
                        user.setId(login.getUserid());
                        userService.updateUser(user);
                        jsonObject.put("code", "200");
                        jsonObject.put("message", "图片上传成功");
                        jsonObject.put("fileName", fileName);
                    } else {
                        jsonObject.put("code", "400");
                        jsonObject.put("massage", "文件上传失败");
                    }
                }
            }
        }
        //显示图片
        return jsonObject;
    }

    @GetMapping(value = "/show")
    public ResponseEntity showPhotos(String fileName) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + File.separator + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private Boolean isImageFile(String fileName) {
        String[] img_type = new String[]{".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        if (fileName == null) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type : img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    @PostMapping(value = "/saveQuestionImage")
    public JSONObject saveQuestionImage(@RequestParam(value = "questionimage")String base64Str[], ServletRequest request) {
        System.out.println(base64Str.length);
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if (token == null) {
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        } else {
            String a  = "";
            for(int i=0;i<base64Str.length;i++){
                User user = new User();
                StringBuffer fileName = new StringBuffer();
                fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
//                File file = new File(path, fileName.toString());
//                base64Str[i] = base64Str[i].replace("data:image/jpeg;base64,", "").replaceAll("[\\s*\t\n\r]","+");
//                byte[] fileBytes = Base64.getDecoder().decode(base64Str[i]);
                a =a+ BASE_URL + fileName.append(".jpg").toString()+"#";
                System.out.println(base64Str[i]);
                System.out.println("a="+a);
                jsonObject.put("我好了", "你呢");
//                try {
//                    FileUtil.writeByteArrayToFile(file, fileBytes);
//                    jsonObject.put("code", "200");
//                    jsonObject.put("message", "图片上传成功");
//                    jsonObject.put("path", user.getAvatar());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println("a="+a);
        }
        return jsonObject;
    }
    @PostMapping(value = "/uploadBase64")
    public JSONObject saveAvatar(@RequestParam(value = "avatar") String base64Str, ServletRequest request) {

        JSONObject jsonObject = new JSONObject();
        //要上传的目标文件存放的路径
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if (token == null) {
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        } else {
            Login login = userService.findUseridByToken(token);
            if (login == null) {
                jsonObject.put("code", "406");
                jsonObject.put("message", "查询失败");
            } else {
                User user = new User();
                StringBuffer fileName = new StringBuffer();
                fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
                if (base64Str.indexOf("data:image/jpg;") != -1) {
                    base64Str = base64Str.replace("data:image/jpg;base64,", "").replaceAll("[\\s*\t\n\r]","+");
                    System.out.println(base64Str);

                    String localPath = fileName.append(".jpg").toString();
                    user.setId(login.getUserid());
                    user.setAvatar(BASE_URL+localPath);
                    userService.updateUser(user);
                    User userInDataBase = userService.findById(login.getUserid());
                    if (userInDataBase.getRole().equals(100)){
                        Experts experts=new Experts();
                        experts.setExpertsid(user.getId());
                        experts.setImg(user.getAvatar());
                        userService.updateExperts(experts);
                    }
                    File file = new File(path, fileName.toString());
                    byte[] fileBytes = Base64.getDecoder().decode(base64Str);
                    try {
                        FileUtil.writeByteArrayToFile(file, fileBytes);
                        jsonObject.put("code", "200");
                        jsonObject.put("message", "图片上传成功");
                        jsonObject.put("path", user.getAvatar());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    jsonObject.put("code", "400");
                    jsonObject.put("message", "格式不正确");
                }
            }
        }

        return jsonObject;
    }
}

