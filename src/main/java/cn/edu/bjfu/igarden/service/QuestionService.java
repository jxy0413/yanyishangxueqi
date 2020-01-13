package cn.edu.bjfu.igarden.service;


import cn.edu.bjfu.igarden.dao.QuestionMapper;
import cn.edu.bjfu.igarden.entity.*;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Cookie on 2019/2/25.
 */
@Service
public class QuestionService {

    @Autowired
    private SolrClient solrClient;
    public static final int PAGE_SIZE = 15;
    public static final int MIN_LIST = 15;
    private QuestionMapper questionMapper;
    @Autowired
    public QuestionService(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }
    public int addQuestion(Question question) {
        return questionMapper.addQuestion(question);
    }
    public int addPlantQuestion(Question question) {
        return questionMapper.addPlantQuestion(question);
    }
    public int adoptQuestion(Question question){
        return  questionMapper.adoptQuestion(question);
    }

    public int updateQuestionUniquekey(Question question){
        return  questionMapper.updateQuestionUniquekey(question);
    }
    public int QuestionHits(Question question){
        return  questionMapper.QuestionHits(question);
    }
    public int changeQuestionStatus(Question question){
        return  questionMapper.changeQuestionStatus(question);
    }
    public Question findQuestionStatusByQuestionId(int id){
        Question question=new Question();
        question.setId(id);
        return questionMapper.findQuestionStatusById(question);
    }
    public List<Question> findQuestionByUserId(int id,int qtype) {
        Question question = new Question();
        question.setUserid(id);
        question.setQtype(qtype);

        List<Question> questionList= questionMapper.findQuestionByUserId(question);

        return questionList;
    }

    public JSONObject findQuestionListByAccept_status(int currentPage,int accept_status,int qtype){
//        PageHelper.startPage(currentPage,PAGE_SIZE,true);
        JSONObject jsonObject =new JSONObject();
        Question question = new Question();
        question.setQtype(qtype);
        question.setAccept_status(accept_status);
        List<Question> questionList= questionMapper.findQuestionListByAcceptStatus(question);
        for(int i =0;i<questionList.size();i++){
            if(questionList.get(i).getImage()!=null){
                questionList.get(i).setImage(questionList.get(i).getImage().split("#")[0]);
            }
        }
        for(Question qu : questionList){
            qu.setCreatetime(qu.getCreatetime().substring(0,19));
        }
        PageInfo<Question> pageInfo = new PageInfo<>(questionList);
        PageBean<Question> pageBean = new PageBean<>(pageInfo);
        jsonObject.put("data",pageInfo.getList());

//        jsonObject.put("nextPage",pageInfo.getNextPage());
        jsonObject.put("totalNum",pageBean.getTotal());
        return jsonObject;
    }
    public JSONObject findQuestionListByStatus(int currentPage,int status,int qtype){
        PageHelper.startPage(currentPage,PAGE_SIZE,true);
        JSONObject jsonObject =new JSONObject();
        Question question = new Question();
        question.setQtype(qtype);
        question.setStatus(status);
        List<Question> questionList= questionMapper.findQuestionListByStatus(question);
        for(int i =0;i<questionList.size();i++){
            if(questionList.get(i).getImage()!=null){
                List<String> images = Arrays.asList(questionList.get(i).getImage().split("#"));
                questionList.get(i).setImageData(images);
                questionList.get(i).setImage(images.get(0));
            }
        }
        for(Question qu : questionList){
            qu.setCreatetime(qu.getCreatetime().substring(0,19));
        }

        PageInfo<Question> pageInfo = new PageInfo<>(questionList);
        PageBean<Question> pageBean = new PageBean<>(pageInfo);
        jsonObject.put("data",pageInfo.getList());
//        jsonObject.put("nextPage",pageInfo.getNextPage());
        jsonObject.put("totalNum",pageBean.getTotal());
        return jsonObject;
    }
    public List<Question> findAll() {
        return questionMapper.findAll();
    }

    public Question findQuestionById(int id){
        Question question =new Question();
        question.setId(id);
        return questionMapper.findQuestionById(question);
    }

//    public List<Question> findQuestionByTimeAndType(int currentPage,int pageSize) {
//        PageHelper.startPage(currentPage, pageSize);
//        List<Question> allItems = questionMapper.findAll();        //全部商品
//        PageBean<Question> pageData = new PageBean<>(currentPage, pageSize);
//        pageData.setItems(allItems);
//        return pageData.getItems();
//    }
    public static int getMin(int[] arr)
    {
        int min = arr[0];
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]<min)
                min = arr[i];
        }
        return min;
    }
    public JSONObject findHotQuestionByHitsAndQtype(int qtype) throws ParseException {
        JSONObject jsonObject=new JSONObject();
        Question question = new Question();
        question.setQtype(qtype);
        List<Question> list = questionMapper.findHotQuestionByHitsAndQtype(question);
        int[] arr={list.size(),MIN_LIST};
        List<Question> questionList = list.subList(0, getMin(arr));
        for(int i =0;i<questionList.size();i++){
            if (questionList.get(i).getCreatetime()!=null){

                SimpleDateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date  date = fmt.parse(questionList.get(i).getCreatetime());
                String s = fmt.format(date);
                questionList.get(i).setCreatetime(s);
            }
            if(questionList.get(i).getImage()!=null){
                List<String> images = Arrays.asList(questionList.get(i).getImage().split("#"));
                questionList.get(i).setImageData(images);
                questionList.get(i).setImage(images.get(0));
            }
        }
        jsonObject.put("data",questionList);
        jsonObject.put("size",questionList.size());
        return jsonObject;
    }
//    public JSONObject findQuestionByHitsAndQtype(int qtype,int currentPage){
//        JSONObject jsonObject=new JSONObject();
///       PageHelper.startPage(currentPage,PAGE_SIZE);
//        Question question = new Question();
//        question.setQtype(qtype);
//        List<Question> list = questionMapper.findQuestionByHitsAndQtype(question);
//        System.out.println(list.size());
//        int[] arr={list.size(),MIN_LIST};
//        List<Question> list2 = list.subList(0, getMin(arr));
//        PageBean<Question> pageBean = new PageBean<>(currentPage,PAGE_SIZE);
//        pageBean.setItems(list2);
//        jsonObject.put("data",list2);
//        jsonObject.put("size",list2.size());
//        return jsonObject;
//        return pageBean.getItems();
//    }
    public Question queryById(int id) throws Exception{
        // 先通过solr查询，查询不到查数据库
        SolrQuery query = new SolrQuery();
        query.setQuery("id:" + id);
        Question question = null;
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                for (SolrDocument document:documentList) {
                    question = new Question();
                    question.setId(id);
                    question.setTitle((String) document.get("title"));
                    question.setContent((String) document.get("content"));
                    question.setImage((String) document.get("image"));
                    question.setUserid((int) document.get("userid"));
                    question.setQtype((int) document.get("qtype"));
                    question.setAnswertype((String) document.get("answertype"));
                    question.setStatus((int) document.get("status"));
                    question.setAccept_status((int) document.get("accept_status"));
                    System.out.println("我调用了solr");
                }
            } else {
                // 从数据库查询
                question = findQuestionById(id);
                System.out.println("我调用了MyBatis");
                if (question != null) {
                    solrClient.addBean(question,1000);
                }
            }

        } catch (SolrServerException e) {
            e.getMessage();
        }
        return question;
    }


}
