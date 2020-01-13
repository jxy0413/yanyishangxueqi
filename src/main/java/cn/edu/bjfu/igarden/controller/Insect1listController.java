package cn.edu.bjfu.igarden.controller;


//import cn.edu.bjfu.igarden.dao.InsectdetailesRepository;
import cn.edu.bjfu.igarden.dao.Insectlist1Repository;
import cn.edu.bjfu.igarden.entity.BaseEntity;
import cn.edu.bjfu.igarden.entity.InsectDetail;
import cn.edu.bjfu.igarden.entity.Insectlist1;
import cn.edu.bjfu.igarden.entity.Page;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
//import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yxy on 2019/1/24.
 */
@RestController
public class Insect1listController {
    @Autowired
    Insectlist1Repository insectlist1Repository;

//    @Autowired
//    InsectdetailesRepository insectdetailesRepository;

//    @GetMapping(value = "/getOne/{id}")
//    public Insectlist1 getOne(@PathVariable Integer id){
//        System.out.println("查找");
//        return insectlist1Repository.findOne(id);
//    }

    @PostMapping(value = "/getOne")
    public Insectlist1 getOne(@RequestParam("id")Integer id){
        System.out.println("查找");
        return insectlist1Repository.findOne(id);
    }
    @PostMapping(value = "bgetOne")
    public BaseEntity bgetOne(@RequestParam("id")Integer id){
        System.out.println("baseentity查找");
        BaseEntity<Insectlist1>baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(insectlist1Repository.findOne(id));
        return baseEntity;
    }

    @PostMapping(value = "bfindAll")
    public BaseEntity bgetAll(){
        System.out.println("baseentity 查找全部");
        BaseEntity<List>baseEntity =new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(insectlist1Repository.findAll());
        return baseEntity;
    }

    @PostMapping(value = "/addOne")
    public Insectlist1 addOne(@RequestParam("kind1")String kind1,@RequestParam("count")Integer count){
        Insectlist1 insectlist = new Insectlist1();
        insectlist.setKind1(kind1);
        insectlist.setCount(count);
        System.out.println("添加");
        return insectlist1Repository.save(insectlist);
    }

//    @PutMapping(value = "/insertOne/{id}")
//    public Insectlist1 updateOne(@PathVariable("id")Integer id,@RequestParam("kind1")String kind1,@RequestParam("count")Integer count){
//        Insectlist1 insect = insectlist1Repository.findById(id);
//        insect.setCount(count);
//        insect.setKind1(kind1);
//        System.out.println("修改");
//        return insectlist1Repository.save(insect);
//    }
    @PutMapping(value = "/insertOne")
    public Insectlist1 updateOne(@RequestParam("id") Integer id,@RequestParam("kind1")String kind1,@RequestParam("count")Integer count){
        Insectlist1 insect = insectlist1Repository.findById(id);
        insect.setCount(count);
        insect.setKind1(kind1);
        System.out.println("修改");
        return insectlist1Repository.save(insect);
    }



    @GetMapping(value = "/insertInsect")
    public Insectlist1 insertInsect(Insectlist1 insectlist1){
        return insectlist1Repository.save(insectlist1);
    }



//    返回删除后余下的所有数据
    @DeleteMapping(value = "/deleteOne/{id}")
    public List<Insectlist1> deleteOne(@PathVariable("id")Integer id){
        insectlist1Repository.delete(id);
        System.out.println("删除");
        return insectlist1Repository.findAll();
    }

    @DeleteMapping(value = "/deleteInsect")
    public String deleteInsect(@RequestParam("id")Integer id){
        insectlist1Repository.delete(id);
        return "删除成功!";
    }

    @GetMapping(value = "/getByKind/{kind1}")
    public List<Insectlist1> getByKind1 (@PathVariable("kind1")String kind1){
        return insectlist1Repository.findByKind1(kind1);
    }

    @PostMapping(value = "/getByKind")
    public List<Insectlist1> getByKind2 (@RequestParam("kind1")String kind1){
        return insectlist1Repository.findByKind1(kind1);
    }




//    @PostMapping(value = "/getElasticList")
//    public List<InsectDetail> getElasticList(Integer pageNumber,String query){
//        if(pageNumber==null)
//            pageNumber=0;
//        SearchQuery searchQuery= getEntitySearchQuery(pageNumber,PAGESIZE,query);
//        Page<InsectDetail> detailPage = insectdetailesRepository.search(searchQuery);
//        return detailPage.getTitle();
//    }



//    elastic测试
//    private Integer PAGESIZE=10;
//
//    private SearchQuery getEntitySearchQuery(int pageNumber,int pagesize,String searchContent){
//        FunctionScoreQueryBuilder functionScoreQueryBuilder= QueryBuilders.functionScoreQuery()
//                .add(QueryBuilders.matchPhraseQuery("title",searchContent),
//                        ScoreFunctionBuilders.weightFactorFunction(100))
//                .add(QueryBuilders.matchPhraseQuery("fangfa",searchContent),
//                        ScoreFunctionBuilders.weightFactorFunction(100))
//                .scoreMode("sum")
//                .setMinScore(10);
//
//        Pageable pageable = new PageRequest(pageNumber,pagesize);
//        return new NativeSearchQueryBuilder()
//                .withPageable(pageable)
//                .withQuery(functionScoreQueryBuilder).build();
//    }





}
