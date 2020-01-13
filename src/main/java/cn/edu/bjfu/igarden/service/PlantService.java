package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.dao.AnswerMapper;
import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.rec_engin.handle.mix_rec;
import cn.edu.bjfu.igarden.rec_engin.map_mix;
import cn.edu.bjfu.igarden.rec_engin.similarity;
import cn.edu.bjfu.igarden.rec_engin.split;
import org.ansj.recognition.impl.StopRecognition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jxy on 2019/7/30.
 */
@Service
public class PlantService {
    @Autowired
    private AnswerMapper plantDetailMapper;

    public PlantDetail rearchDetailById(int id) {
        return plantDetailMapper.rearchDeatilByid(id);
    }


    public PlantDetail queryPlantByName(String plant_name) {

        return plantDetailMapper.queryPlantByName(plant_name);
    }


    public DiseaseTable queryBingChongByName(String diseaseName) {
        return plantDetailMapper.queryBingChongByName(diseaseName);
    }

    public void updateShiBieCount(String plant_name, int shibieCount) {
        plantDetailMapper.updateShiBieCount(plant_name, shibieCount);
    }

    public void updateShiBieCountBingChong(String diseaseName, int shibieCount) {
        plantDetailMapper.updateShiBieCountBingChong(diseaseName, shibieCount);
    }

    public List<diseasePlant> queryAll() {
        return plantDetailMapper.queryAll();
    }


    public List<sql_sort> queryChinayuanidByplantname(String plantname) {
        return plantDetailMapper.queryChinayuanidByplantname(plantname);
    }

    public ChinayuanlinDetail queryChinayuanidBychinayuanlinId(int chinayuanlin_id) {
        return plantDetailMapper.queryChinayuanidByplantlinId(chinayuanlin_id);
    }
    public List<plantbieming> queryplantbiemingByplantname(String plantname) {
        return plantDetailMapper.queryplantbiemingByplantname(plantname);
    }
    public String queryplantnameByplantbieming(String plant_bieming) {
        return plantDetailMapper.queryplantnameByplantbieming(plant_bieming);
    }

    public List<disease_rule> queryDiseaseById(int ruleid) {
        return plantDetailMapper.queryDiseaseById(ruleid);
    }

    public List<PlantDetail> queryCountNameCiShu() {
        return plantDetailMapper.queryCountNameciShu();
    }

    public void updateShiBieCountPlant(String plant_name, int shibieCount) {
         plantDetailMapper.queryCountPlantName(plant_name, shibieCount);
    }

    public int updateAlert(int id) {
       return plantDetailMapper.updateAlert(id);
    }

    public int updateAlertRevoke(int id) {
        return plantDetailMapper.updateAlertRevoke(id);
    }


    public int queryDidianByName(String s) {
        return plantDetailMapper.queryDidianByName(s);
    }

    public List<addDiseaseCount> queryQianSi() {
        return plantDetailMapper.queryQianSi();
    }

    public int querydidianByNamePlant(String s) {
        return plantDetailMapper.queryDidianByNamePlant(s);
    }


    public List<addDiseaseCount> queryDiseaseCountByTimeOneDay() {
        return plantDetailMapper.queryDiseaseCountByTimeOneDay();
    }

    public List<addPlantCount> queryPlantCountByTimeOneDay() {
        return plantDetailMapper.queryPlantCountByTimeOneDay();
    }

    public List<addPlantCount> queryQianSiByPlant() {
        return plantDetailMapper.queryQianSiByPlant();
    }

    public int countLiShiShiBieByZhiWu() {
        return plantDetailMapper.countLiShiShiBieByZhiWu();
    }

    public int countLiShiShiBieByBingChong() {
        return  plantDetailMapper.countLiShiShiBieByBingChong();
    }

    public int countLiShiShiBieByZhiWuToday(String today) {
        return plantDetailMapper.countLiShiShiBieByZhiWuToday(today);
    }

    public int countLiShiShiBieByBingChongToday(String today) {
        return plantDetailMapper.countLiShiShiBieByBingChongToday(today);
    }

    public int countUser() {
        return plantDetailMapper.countUser();
    }

    public List<Experts> queryExpertRound() {
        return plantDetailMapper.queryExpertRound();
    }

    public double queryLiShiShiBieByZhiWuByType1() {
        return plantDetailMapper.queryLiShiShiBieByZhiWuByType1();
    }

    public double queryLiShiShiBieByZhiWuByType2() {
        return plantDetailMapper.queryLiShiShiBieByZhiWuByType2();
    }


    public List<addDiseaseCount> queryDiseaseCountByTime() {
        return plantDetailMapper.queryDiseaseCountByTime();
    }

    public List<addPlantCount> queryPlantCountByTime() {
        return plantDetailMapper.queryPlantCountByTime();
    }
    public List<PlantDetail> getPlantBybianhao(String devision){
        PlantDetail plantDetail =new PlantDetail();
        plantDetail.setDevision(devision);
        return plantDetailMapper.getPlantBybianhao(plantDetail);
    }
    public List<historyPlant> getHistoryByUsername(){
        return plantDetailMapper.getHistoryByUsername();
    }
    public List<historyPlant> getUser(){
        return plantDetailMapper.getUser();
    }

    public PlantDetail getPlantByZhiwuming(String plant_name){
        PlantDetail plantDetail =new PlantDetail();
        plantDetail.setPlant_name(plant_name);
        return plantDetailMapper.getPlantByZhiwuming(plantDetail);
    }
    public List<User> getAllUsers() {
        return plantDetailMapper.getAllUsers();
    }
    public List<PlantTuijian> getAllPapers() {
        return plantDetailMapper.getAllPapers();
    }
    public List<Like> findLikesByUser(int uid) {
        return plantDetailMapper.findLikesByUser(uid);
    }
    public List<Like1> findLikesByUser1(int uid) {
        return plantDetailMapper.findLikesByUser1(uid);
    }
    public PlantTuijian findPaperById(int id) {
        return plantDetailMapper.findPaperById(id);
    }
    public List<Users> getAllUsers1() {
        return plantDetailMapper.getAllUsers1();
    }
    public List<Paper> getAllPapers1() {
        return plantDetailMapper.getAllPapers1();
    }
    public Paper findPaperById1(int pid) {
        return plantDetailMapper.findPaperById1(pid);
    }
    public List<PlantTuijian> addTopNPapers(List<PlantTuijian> recomLists) {
        List<PlantTuijian> plantTuijians=recomLists;
        List<PlantTuijian> res=plantDetailMapper.findTopNPapers();
        int size=5-plantTuijians.size();
        System.out.println(size);
        for(int i=0;i<size;i++){
            plantTuijians.add(res.get(i));
            System.out.println(res.get(i).getPlant_name()+i);
        }
        return plantTuijians;
    }
    public List<Map.Entry<Integer,Double>> recommendPlant(int id) {
        List<Map.Entry<Integer,Double>> infoId = new ArrayList<Map.Entry<Integer,Double>>();
        infoId= mix_rec.countSim(id);

//        List<Map.Entry<Integer,Double>> infoIds = new ArrayList<Map.Entry<Integer,Double>>();
//        List<Map.Entry<Integer,Double>> novel = novelty.getRes(novelty.getNovelty(id));
        /*
        给用户添加新颖度
         */
//        for (int i = 0; i < 20-novel.size(); i++) {
//            infoIds.add(infoId.get(i));
//        }
//        for (int i = 0; i < novel.size(); i++) {
//            infoIds.add(novel.get(i));
//        }
        return infoId;
    }
    public String getColumn(String column,int id) {
        return plantDetailMapper.getColumn(column,id);
    }
    public List<String> getColumn2() {
        return plantDetailMapper.getColumn2();
    }
    public List<String> getColumn3() {
        return plantDetailMapper.getColumn3();
    }
    public ArrayList<Integer> idlist() {
        return plantDetailMapper.idlist();
    }
    public ArrayList<Integer> queryLike(int user_id) {
        return plantDetailMapper.queryLike(user_id);
    }
    public String getColumnWithId(int id) {
        return plantDetailMapper.getColumnWithId(id);
    }
    public String getColumnWithId2(int id) {
        return plantDetailMapper.getColumnWithId(id);
    }
    public String getUserWithId(int id) {
        return plantDetailMapper.getUserWithId(id);
    }
    public String getCaipuWithId(int id) {
        return plantDetailMapper.getUserWithId(id);
    }
    public Map<String, double[]> getUserVector(int id, Set<String> expectedNature, StopRecognition filter) {//得到用户的向量空间,
        map_mix mix = new map_mix();
        similarity sim = new similarity();
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Integer> userLikeList = plantDetailMapper.queryLike(id);
        Map<String, double[]> vectorSpace = new HashMap<String, double[]>();
        split sw = new split();
        for (int i = 0; i < userLikeList.size(); i++) {
//            String sql = "SELECT `菜谱`.`" + column + "` FROM `菜谱` WHERE `菜谱`.`菜谱ID`=" + userLikeList.get(i);
//            rs = sta.executeQuery(sql);

            String handle = plantDetailMapper.getUserWithId(plantDetailMapper.queryLike(id).get(i));

            if (handle != null) {//注意有为空的情况
//                        sw.splitWord(handle);
//                        result=sw.splitWordtoArrDelUseless(handle);
                if (filter != null) {
                    result = sw.getFilterWord(handle, expectedNature, filter);
                } else {
                    result = sw.getFilterWord(handle, expectedNature);
                }
                mix.mapMix(vectorSpace, sim.getVector(result, 0));
            } else {
                System.out.println("null");
            }
        }
        return vectorSpace;
    }
    public Map<String, double[]> getRecipeVector(int i,Set<String> expectedNature,StopRecognition filter) {//得到某个菜谱的向量空间,i为第几个菜谱，并非菜谱id
        similarity similarity = new similarity();
        ArrayList<String> result = new ArrayList<String>();

        split sw = new split();
//            String sql = "SELECT `菜谱`.`"+column+"` FROM `菜谱` WHERE `菜谱`.`菜谱ID`=" + disDAO.idlist.get(i);
//            rs = sta.executeQuery(sql);

        String handle = plantDetailMapper.getCaipuWithId(plantDetailMapper.idlist().get(i));
        if (handle != null) {//注意有为空的情况
//                    sw.splitWord(handle);
//                    result = sw.splitWordtoArrDelUseless(handle);
            if (filter != null) {
                result = sw.getFilterWord(handle, expectedNature, filter);
            } else {
                result = sw.getFilterWord(handle, expectedNature);
            }
        } else {
            System.out.println("null");
        }
        return similarity.getVector(result, 1);
    }
    public PlantTuijian getRecipeById(int id){
        PlantTuijian recipeVo =new PlantTuijian();
        recipeVo.setId(id);
        return plantDetailMapper.getRecipeById(id);
    }
}