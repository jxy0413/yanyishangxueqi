package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cookie on 2019/2/27.
 */
@Mapper
public interface AnswerMapper {
    List<Answer> findAnswerByQuestionId(Answer answer);
    List<Answer> findAnswerByUserId(Answer answer);
    int addAnswerByUserId(Answer answer);
    List<Answer> findAnswerByUserIdAndQuestionId(Answer answer);
    int changeAnswerAdopt(Answer answer);
    Answer findAnswerById(Answer answer);
    List<Answer> findQuestionListofAnswerByUserIdAndQtype(Answer answer);
    int updateAnswerLikeTimes(Answer answer);

    PlantDetail rearchDeatilByid(int id);

    PlantDetail queryPlantByName(String plant_name);


    void updateShiBieCount(@Param("plant_name") String plant_name, @Param("shibieCount") int shibieCount);

    DiseaseTable queryBingChongByName(String diseaseName);

    void updateShiBieCountBingChong(@Param("disease_name") String disease_name, @Param("shibieCount")int shibieCount);

    List<diseasePlant> queryAll();


    List<sql_sort> queryChinayuanidByplantname(String plantname);

    ChinayuanlinDetail queryChinayuanidByplantlinId(int chinayuanlin_id);
    disease_inference searchInference(String result);
    disease_rule searchAllrule(Integer rule);
    List<plantbieming> queryplantbiemingByplantname(String plantname);
    String queryplantnameByplantbieming(String plant_bieming);
    List<Parkinfo> selectByExampleList();
    List<ParkinfoWithBLOBs> selectByExampleWithBLOBs();
    List<ParkinfoWithBLOBs> findParkListByName(ParkinfoWithBLOBs parkinfoWithBLOBs);
    List<Flowerinfo> selectByFlowerExample();
    List<Flowerinfo> findFlowerListByName(Flowerinfo flowerinfo);

    List<disease_rule> queryDiseaseById(int ruleid);

    List<PlantDetail> queryCountNameciShu();

    void queryCountPlantName(@Param("plant_name")String plant_name, @Param("shibieCount")int shibieCount);

    int updateAlert(int id);

    int updateAlertRevoke(int id);


    int queryDidianByName(String name);

    List<addDiseaseCount> queryQianSi();

    int queryDidianByNamePlant(String s);

    List<addPlantCount> queryQianSiByPlant();

    int countLiShiShiBieByZhiWu();

    int countLiShiShiBieByBingChong();

    int countLiShiShiBieByZhiWuToday(String today);

    int countLiShiShiBieByBingChongToday(String today);

    int countUser();

    List<Experts> queryExpertRound();

    double queryLiShiShiBieByZhiWuByType1();

    double queryLiShiShiBieByZhiWuByType2();

    List<addDiseaseCount> queryDiseaseCountByTime();

    List<addPlantCount> queryPlantCountByTime();

    List<addDiseaseCount> queryDiseaseCountByTimeOneDay();

    List<addPlantCount> queryPlantCountByTimeOneDay();

    List<PlantDetail> getPlantBybianhao(PlantDetail plantDetail);

    List<historyPlant> getHistoryByUsername();

    List<historyPlant> getUser();

    PlantDetail getPlantByZhiwuming(PlantDetail plantDetail);
    List<User> getAllUsers();
    List<PlantTuijian> getAllPapers();
    List<Like> findLikesByUser(@Param("uid")int uid);
    PlantTuijian findPaperById(@Param("id")int id);
    List<Users> getAllUsers1();
    List<Paper> getAllPapers1();
    Paper findPaperById1(@Param("pid")int pid);
    List<Like1> findLikesByUser1(@Param("uid")int uid);
    List<PlantTuijian> findTopNPapers();
    String getColumn(@Param("column")String column,@Param("id")int id);
    List<String> getColumn2();
    List<String> getColumn3();
    ArrayList<Integer> idlist();
    ArrayList<Integer> queryLike(@Param("user_id")int user_id);
    String getColumnWithId(@Param("id")int id);//simdao.getColumnWithId 菜谱名称withID   植物名
    String getColumnWithId2(@Param("id")int id);//simdao.getColumnWithId 做法withID    描述
    String getUserWithId(@Param("id")int id);//simdao.getUserVector    原料    表型特征
    String getCaipuWithId(@Param("id")int id);//simdao.getRecipeVector     原料    表型特征
    PlantTuijian getRecipeById(int id);//id找菜谱
}
