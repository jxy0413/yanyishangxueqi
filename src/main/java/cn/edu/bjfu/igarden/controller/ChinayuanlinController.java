package cn.edu.bjfu.igarden.controller;
import cn.edu.bjfu.igarden.dao.*;
import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.service.UserService;
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
public class ChinayuanlinController {

    @Autowired
    ChinayuanlinlistflowerRepository chinayuanlinlistflowerRepository;
    @Autowired
    ChinayuanlinlistcaopingRepository chinayuanlinlistcaopingRepository;
    @Autowired
    ChinayuanlinlistdibeiRepository chinayuanlinlistdibeiRepository;
    @Autowired
    ChinayuanlinlistfruitRepository chinayuanlinlistfruitRepository;
    @Autowired
    ChinayuanlinlistqiaoguanRepository chinayuanlinlistqiaoguanRepository;
    @Autowired
    ChinayuanlinlistzhuleiRepository chinayuanlinlistzhuleiRepository;
    @Autowired
    Chinayuanlinlist1Repository chinayuanlinlist1Repository;
    @Autowired
    ChinayuanlindetailRepository chinayuanlindetailRepository;


    private UserService userService;
    @Autowired
    public ChinayuanlinController(UserService userService){
        this.userService = userService;

    }
    //设置分页查询的页面大小
   public static final int PAGESIZE = 15;

//  1  Chinayuanlinlist1Repository的接口
    @PostMapping(value = "/getChinayuanlinlist1sum")
    public BaseEntity getChinayuanlinlist1sum(ServletRequest servletRequest){
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String token = req.getHeader("token");
        BaseEntity<List>baseEntity = new BaseEntity<>();
        if(token == null){
            baseEntity.setCode(210);
            baseEntity.setMessage("无token");
        }else {
            Login loginInfo =userService.findUseridByToken(token);
            if(loginInfo == null){
                baseEntity.setCode(211);
                baseEntity.setMessage("找不到登录信息");
            }else {
                baseEntity.setCode(200);
                baseEntity.setMessage("success");
                baseEntity.setData(chinayuanlinlist1Repository.findAll());
            }
        }

        return baseEntity;
    }


//    ChinayuanlindetailRepository接口
    @PostMapping(value = "findChinayuanlinDetailById")
    public BaseEntity findChinayuanlinDetailById(@RequestParam("id")int id){
        BaseEntity<ChinayuanlinDetail>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(chinayuanlindetailRepository.findChinayuanlinDetailById(id));
        return baseEntity;
    }

    @PostMapping(value = "findChinayuanlinDetailByTitle")
    public BaseEntity findChinayuanlinDetailByTitle(@RequestParam("title")String title){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
//        baseEntity.setData(chinayuanlindetailRepository.findChinayuanlinDetailByTitle(title));
        baseEntity.setData(chinayuanlindetailRepository.findChinayuanlinDetailByTitleLike(title));
        return baseEntity;
    }


//    ChinayuanlinlistflowerRepository的接口

    @PostMapping(value = "/findAllFlower")
    public BaseEntity findAll(@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistflower>all = chinayuanlinlistflowerRepository.findAll(pageable).iterator();
        List<Chinayuanlinlistflower>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if (all == null){
            baseEntity.setMessage("fail");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }

    }

    @PostMapping(value = "/findFlowerByZbc")
    public BaseEntity findByZbc(@RequestParam("zbc")String zbc,@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistflower>all  = chinayuanlinlistflowerRepository.findFlowerByZbc(pageable,zbc).iterator();
        List<Chinayuanlinlistflower>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if(all == null){
            baseEntity.setMessage("查询失败");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }

    @PostMapping(value = "/findFlowerByZname")
    public BaseEntity findByZname(@RequestParam("zname")String zname){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(chinayuanlinlistflowerRepository.findFlowerByZname(zname));
        return baseEntity;
    }

//    ChinayuanlinlistcaopingRepository的接口
    @PostMapping(value = "/findAllCaoping")
    public BaseEntity findAllCaoping(@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistcaoping>all = chinayuanlinlistcaopingRepository.findAll(pageable).iterator();
        List<Chinayuanlinlistcaoping>list = new ArrayList<>();
        while (all.hasNext()){
           list.add(all.next());
        }
        if (all == null){
            baseEntity.setMessage("fail");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }

    @PostMapping(value = "/findCaopingByZbc")
    public BaseEntity findCaopingByZbc(@RequestParam("zbc")String zbc,@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistcaoping>all  = chinayuanlinlistcaopingRepository.findCaopingByZbc(pageable,zbc).iterator();
        List<Chinayuanlinlistcaoping>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if(all == null){
            baseEntity.setMessage("查询失败");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }
    @PostMapping(value = "/findCaopingByZname")
    public BaseEntity findCaopingByZname(@RequestParam("zname")String zname){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(chinayuanlinlistcaopingRepository.findCaopingByZname(zname));
        return baseEntity;
    }

    //   chinayuanlinlistdibeiRepository的接口
    @PostMapping(value = "/findAllDibei")
    public BaseEntity findAllDibei(@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistdibei>all = chinayuanlinlistdibeiRepository.findAll(pageable).iterator();
        List<Chinayuanlinlistdibei>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if (all == null){
            baseEntity.setMessage("fail");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }

    @PostMapping(value = "/findDibeiByZbc")
    public BaseEntity findDibeiByZbc(@RequestParam("zbc")String zbc,@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistdibei>all  = chinayuanlinlistdibeiRepository.findByZbc(pageable,zbc).iterator();
        List<Chinayuanlinlistdibei>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if(all == null){
            baseEntity.setMessage("查询失败");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }
    @PostMapping(value = "/findDibeiByZname")
    public BaseEntity findDibeiByZname(@RequestParam("zname")String zname){
        BaseEntity<Chinayuanlinlistdibei>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(chinayuanlinlistdibeiRepository.findByZname(zname));
        return baseEntity;
    }


    //    chinayuanlinlistfruitRepository的接口
    @PostMapping(value = "/findAllFruit")
    public BaseEntity findAllFruit(@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistfruit>all = chinayuanlinlistfruitRepository.findAll(pageable).iterator();
        List<Chinayuanlinlistfruit>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if (all == null){
            baseEntity.setMessage("fail");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }

    @PostMapping(value = "/findFruitByZbc")
    public BaseEntity findFruitByZbc(@RequestParam("zbc")String zbc,@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistfruit>all  = chinayuanlinlistfruitRepository.findByZbc(pageable,zbc).iterator();
        List<Chinayuanlinlistfruit>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if(all == null){
            baseEntity.setMessage("查询失败");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }
    @PostMapping(value = "/findFruitByZname")
    public BaseEntity findFruitByZname(@RequestParam("zname")String zname){
        BaseEntity<Chinayuanlinlistfruit>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(chinayuanlinlistfruitRepository.findByZname(zname));
        return baseEntity;
    }


    //    chinayuanlinlistqiaoguanRepository的接口
    @PostMapping(value = "/findAllQiaoguan")
    public BaseEntity findAllQiaoguan(@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistqiaoguan>all = chinayuanlinlistqiaoguanRepository.findAll(pageable).iterator();
        List<Chinayuanlinlistqiaoguan>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if (all == null){
            baseEntity.setMessage("fail");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }

    @PostMapping(value = "/findQiaoguanByZbc")
    public BaseEntity findQiaoguanByZbc(@RequestParam("zbc")String zbc,@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistqiaoguan>all  = chinayuanlinlistqiaoguanRepository.findByZbc(pageable,zbc).iterator();
        List<Chinayuanlinlistqiaoguan>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if(all == null){
            baseEntity.setMessage("查询失败");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }
    @PostMapping(value = "/findQiaoguanByZname")
    public BaseEntity findQiaoguanByZname(@RequestParam("zname")String zname){
        BaseEntity<Chinayuanlinlistqiaoguan>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(chinayuanlinlistqiaoguanRepository.findByZname(zname));
        return baseEntity;
    }


    //    chinayuanlinlistzhuleiRepository的接口
    @PostMapping(value = "/findAllZhulei")
    public BaseEntity findAllZhulei(@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistzhulei>all = chinayuanlinlistzhuleiRepository.findAll(pageable).iterator();
        List<Chinayuanlinlistzhulei>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if (all == null){
            baseEntity.setMessage("fail");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }

    @PostMapping(value = "/findZhuleiByZbc")
    public BaseEntity findZhuleiByZbc(@RequestParam("zbc")String zbc,@RequestParam("page")int page){
        BaseEntity<List>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);

        Pageable pageable = new PageRequest(page,PAGESIZE,Sort.Direction.ASC,"id");
        Iterator<Chinayuanlinlistzhulei>all  = chinayuanlinlistzhuleiRepository.findByZbc(pageable,zbc).iterator();
        List<Chinayuanlinlistzhulei>list = new ArrayList<>();
        while (all.hasNext()){
            list.add(all.next());
        }
        if(all == null){
            baseEntity.setMessage("查询失败");
            return baseEntity;
        }else {
            baseEntity.setMessage("success");
            baseEntity.setData(list);
            return baseEntity;
        }
    }
    @PostMapping(value = "/findZhuleiByZname")
    public BaseEntity findZhuleinByZname(@RequestParam("zname")String zname){
        BaseEntity<Chinayuanlinlistzhulei>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(chinayuanlinlistzhuleiRepository.findByZname(zname));
        return baseEntity;
    }

    @PostMapping(value = "/searchChinayuanlinDetail")
    public BaseEntity searchChinayuanlinDetail(@RequestParam("title")String title){
        BaseEntity<List> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
//        baseEntity.setData(chinayuanlindetailRepository.findChinayuanlinDetailByTitleLike(title));
        baseEntity.setData(chinayuanlindetailRepository.findByTitle(title));
        return baseEntity;
    }

    @PostMapping(value = "/chinayuanlinReadTimesAdd")
    public BaseEntity chinayuanlinReadTimesAdd(@RequestParam("title")String title,ServletRequest servletRequest){
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String token = req.getHeader("token");
        BaseEntity baseEntity = new BaseEntity();
        if(token == null){
            baseEntity.setCode(210);
            baseEntity.setMessage("无token");
        }else {
            Login loginInfo = userService.findUseridByToken(token);
            if (loginInfo == null) {
                baseEntity.setCode(211);
                baseEntity.setMessage("找不到登录信息");
            } else {
                ChinayuanlinDetail chinayuanlinDetail = chinayuanlindetailRepository.findChinayuanlinDetailByTitle(title);
                if(null !=chinayuanlinDetail){
                    chinayuanlinDetail.setReadtimes(chinayuanlinDetail.getReadtimes() + 1);
                    ChinayuanlinDetail saveone= chinayuanlindetailRepository.save(chinayuanlinDetail);
                    saveone.setTimenow(saveone.getTimenow().substring(0,19));
                    baseEntity.setData(saveone);
                    System.out.println("阅读次数增加成功");
                    baseEntity.setCode(200);
                    baseEntity.setMessage("success");
                }else {
                    baseEntity.setCode(400);
                    baseEntity.setMessage("success");
                }

            }
        }
        return  baseEntity;
    }

}
