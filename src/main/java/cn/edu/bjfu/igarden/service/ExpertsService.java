package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.dao.ExpertsMapper;
import cn.edu.bjfu.igarden.entity.Experts;
import cn.edu.bjfu.igarden.entity.Invite;
import cn.edu.bjfu.igarden.entity.User;
import cn.edu.bjfu.igarden.entity.chat;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxy on 2019/5/6.
 */
@Service
public class ExpertsService {


    private ExpertsMapper expertsMapper;
    @Autowired
    public ExpertsService(ExpertsMapper expertsMapper){
        this.expertsMapper = expertsMapper;
    }
    public List<Experts> getAllExperts(){
        return expertsMapper.getAllExperts();
    }
    public List<Experts>getExpertsLstByType(String type){
        Experts experts = new Experts();
        experts.setType(type);
        return expertsMapper.getExpertsListByType(experts);
    }
    public Experts getExpertsById(int id){
        Experts experts = new Experts();
        experts.setExpertsid(id);
        return expertsMapper.getExpertById(experts);
    }
    public List getExertsByName(String name){
        Experts experts = new Experts();
        experts.setName(name);
        return expertsMapper.getExpertByName(experts);
    }
    public int insertInviteRecord(Invite invite) {
        return expertsMapper.insertInviteRecord(invite);
    }
    public  int addChat(chat chat) {
        return expertsMapper.addChat(chat);
    }
    public List<List<chat>> getChat(int  userid){
        List<List<chat>> list=new ArrayList<>();
        chat chat = new chat();
        chat.setTouserid(userid);
        List<Integer> userList=new ArrayList<>();
        userList=expertsMapper.getChatUserList(chat);
        System.out.println(userList.size());
        for (int i = 0; i < userList.size(); i++) {
            chat chatTemp=new chat();
            chatTemp.setTouserid(userid);
            chatTemp.setUserid(userList.get(i));
            List<chat> all=new ArrayList<>();
            all=expertsMapper.getChatListByUser(chatTemp);
            list.add(all);
            }

        return list;
    }

    public List<List<chat>> getChatWithExpert(int  userid,int expertid){
        List<List<chat>> list=new ArrayList<>();
        chat chat = new chat();
        chat.setTouserid(expertid);
        chat.setUserid(userid);
        list.add(expertsMapper.getChatListByUser(chat));
        return list;
    }







    public List<chat> readChat(int userid,int touserid){
        List<chat> setRead=new ArrayList<>();
        chat chat = new chat();
        chat.setUserid(userid);
        chat.setTouserid(touserid);
        setRead=expertsMapper.getChatListByUser(chat);
        for (int i = 0; i <setRead.size() ; i++) {
            setRead.get(i).setReadtime(1);
            expertsMapper.updateChatByreadtime(setRead.get(i));
        }
        return  setRead;
    }


}
