package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cookie on 2019/1/17.
 */
@Mapper
public interface UserMapper {
    User findOne(User user);
    List<User> findExpert();
    List<Invite> checkUnReadQuestionList(Invite invite);
    Invite checkUnReadQuestion(Invite invite);
    int changeUnReadQuestion(Invite invite);
    Invite findUnReadQuestion(Invite invite);
    int insertExpert(Experts experts);
    int addUser(User user);
    int addExperts(User user);
    int update(User user);
    int updateExperts(Experts experts);
    int addToken(Login login);
    int updateToken(Login login);
    Login findIdByToken(Login login);
    Login findUseridByToken(Login login);
    Login findByUserId(Login login);
    int findUserIdByToken(String token);
    Invite remindTheNews(Invite invite);

    int changePassword(User user);

    User findByUserPhoneNum(String mobile);

    void isRead(@Param("questionId")int questionId,@Param("expertId")int expertId);

    Invite [] queryExpertId(int questionId);

    addDiseaseCount queryDiseaseCount(String diseaseName);

    void updateDiseaseCount(@Param("diseaseName") String diseaseName,@Param("paizhaoCount") int paizhaoCount);

    int addDiseaseCount(@Param("type") int type,@Param("diseaseName") String diseaseName,@Param("diseaseTime") String diseaseTime,@Param("address") String address,@Param("location") String location);

    int addPlantCount(@Param("type") int type,@Param("plantName") String plantName,@Param("diseaseTime") String diseaseTime,@Param("address") String address,@Param("location") String location);
    ArrayList<User> getAllUser ();
    /**
     * 插入一条数据
     * @param data Map中包含id,username,password
     */

}
