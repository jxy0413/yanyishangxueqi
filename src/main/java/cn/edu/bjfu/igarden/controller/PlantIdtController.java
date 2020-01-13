package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.BaseEntity;
import cn.edu.bjfu.igarden.entity.Plant;
import cn.edu.bjfu.igarden.entity.PlantList;
import cn.edu.bjfu.igarden.model.PlantIdtImpl;
import cn.edu.bjfu.igarden.util.FileUtil;
import cn.edu.bjfu.igarden.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PlantIdtController {
    @Autowired
    private SignController signController;
    @Autowired
    private PlantIdtImpl plantIdt;

    @PostMapping(value = "/getPlantList")
    public BaseEntity<List<PlantList.ResultBean>> uploadImgBase64(@RequestParam("img") String img) {
        return plantIdt.uploadImgBase64(img);
    }

    @GetMapping(value = "/getPlant")
    public BaseEntity<Plant.ResultBean> getPlant(@RequestParam("code") String infoUrl, HttpServletResponse response) throws Exception{
        return plantIdt.getPlant(infoUrl,response);
    }

    // Duplicate 处理文件上传
    @RequestMapping(value = "/testuploadimg", method = RequestMethod.POST)
    public PlantList uploadImg(@RequestParam("file") MultipartFile file,
                               HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            return null;
        }
        FileUtil.reduceImg(filePath, fileName, 200, 200);
        File file1 = new File(filePath + "thumb" + fileName);
        String base64;
        if (file1.exists()) {
            base64 = FileUtil.getImageStr(filePath + "thumb" + fileName);
        } else {
            base64 = FileUtil.getImageStr(filePath + fileName);
        }

        //返回json
        return signController.getFlowerList(base64);
    }

    // Duplicate
    @PostMapping(value = "/postImg")
    public String postImg(@RequestParam("file") MultipartFile file) {
        return file.getOriginalFilename();
    }

    // Duplicate
    @GetMapping(value = "/testtest")
    public Map test(@RequestParam("name") String name, @RequestParam("page") int page) {
        Map map = new HashMap();
        map.put("test", 123);
        return map;
    }
}
