package cn.edu.bjfu.igarden.service;


import cn.edu.bjfu.igarden.dao.UserMapper;
import cn.edu.bjfu.igarden.entity.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.annotations.Param;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cookie on 2019/1/24.
 */
@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    private SolrClient solrClient;
    @Autowired
    public UserService(UserMapper userMapper) {

        this.userMapper = userMapper;
    }


    public String passwordToHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            byte[] src = digest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            // 字节数组转16进制字符串
            // https://my.oschina.net/u/347386/blog/182717
            for (byte aSrc : src) {
                String s = Integer.toHexString(aSrc & 0xFF);
                if (s.length() < 2) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }

    public User findById(int id) {
        User user = new User();
        user.setId(id);
        return userMapper.findOne(user);
    }
    public List<User> findExpert() {
        return userMapper.findExpert();
    }
    public List<Invite> checkUnReadQuestionList(int id) {
        Invite invite = new Invite();
        invite.setExpertId(id);
        return userMapper.checkUnReadQuestionList(invite);
    }
    public Invite checkUnReadQuestion(int questionId,int expertId) {
        Invite invite = new Invite();
        invite.setQuestionId(questionId);
        invite.setExpertId(expertId);
        return userMapper.checkUnReadQuestion(invite);
    }
//    public Login findByTokenId(Integer id) {
//        User user = new User();
//        Login login = new Login();
//        login.setUserid(user.getId());
//        return tokenMapper.findOne(login);
//    }
    public int addUser(User user) {
        String passwordHash =  passwordToHash(user.getPassword());
        user.setPassword(passwordHash);
        return userMapper.addUser(user);
    }

    public int insertExperts(Experts experts){
        return userMapper.insertExpert(experts);
    }
    public int updateExperts(Experts experts){
        return  userMapper.updateExperts(experts);
    }



    public int addExperts(User user) {
        String passwordHash =  passwordToHash(user.getPassword());
        user.setPassword(passwordHash);
        return userMapper.addExperts(user);
    }
    public int addToken(Login login) {
       // User user = new User();
        login.setToken(login.getToken());
        return userMapper.addToken(login);
    }
    public int changeUnReadQuestion(Invite invite){
        return userMapper.changeUnReadQuestion(invite);
    }
    public Invite remindTheNews(int expertId){
        Invite invite =new Invite();
        invite.setExpertId(expertId);
        invite.setIsread(0);
        return userMapper.remindTheNews(invite);
    }
    public int updateUser(User user){
        return userMapper.update(user);
    }
    public User findByUsername(String username) {
        User user = new User();
        user.setUsername(username);

        return userMapper.findOne(user);
    }
    public  Login findByUserId(int id){
        Login login =new Login();
        login.setUserid(id);
        return userMapper.findByUserId(login);
    }
    public Login findUseridByToken(String token) {
        Login login = new Login();
        login.setToken(token);
        return userMapper.findUseridByToken(login);
    }
    public Login findIdByToken(String token) {
        Login login = new Login();
        login.setToken(token);
        return userMapper.findIdByToken(login);
    }
    public int updateToken(Login login) {
        return userMapper.updateToken(login);
    }

    public User findByUser(String username) {
        User param = new User();
        param.setUsername(username);
        return userMapper.findOne(param);
    }
    public boolean compareUsername(User user, User userInDataBase) {
        return user.getUsername()
                .equals(userInDataBase.getUsername());
    }
    public boolean comparePassword(User user, User userInDataBase) {
        return passwordToHash(user.getPassword())      // 将用户提交的密码转换为 hash
                .equals(userInDataBase.getPassword()); // 数据库中的 password 已经是 hash，不用转换
    }
    public List<User> recommendByKeywords(String de) throws Exception{
        List<User> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String type = "expertstag:" + de;
        String classification = " OR classification:" + de;
        String research = " OR research:" + de;
        String description = " OR description:" + de;
        query.set("q",type+classification+research+description);
//        query.set("q",content + title+type+classification+research);
        query.setStart(0);
        query.setRows(20);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<User>>() {
                }.getType());
            }
            else {
                list= findExpert();
            }
        } catch (SolrServerException e) {
            e.getMessage();
        }
        return list;
    }

    public int changePassword(User user){

        return userMapper.changePassword(user);
    }

    public User findByUserPhoneNum(String mobile) {
        return userMapper.findByUserPhoneNum(mobile);
    }

    public void isRead(@Param("questionId")int questionId,@Param("expertId")int expertId) {
        userMapper.isRead(questionId,expertId);
    }


    public Invite[] queryExpertId(int questionId) {
       return userMapper.queryExpertId(questionId);
    }

    public addDiseaseCount queryDiseaseCount(String diseaseName) {
        return userMapper.queryDiseaseCount(diseaseName);
    }



    public void updateDiseaseCount(String diseaseName,int paizhaoCount) {
        userMapper.updateDiseaseCount(diseaseName,paizhaoCount);
    }

    public int addDiseaseCount(int type, String diseaseName, String diseaseTime, String address, String location) {
       return userMapper.addDiseaseCount(type,diseaseName,diseaseTime,address,location);
    }

    public int addPlantCount(int type, String plantName, String diseaseTime, String address, String location) {
        return userMapper.addPlantCount(type,plantName,diseaseTime,address,location);
    }
    public ArrayList<User> getAllUser() {
        return userMapper.getAllUser();
    }
}
