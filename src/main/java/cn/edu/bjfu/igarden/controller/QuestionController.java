package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.dao.QuestionMapper;
import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.service.ExpertsService;
import cn.edu.bjfu.igarden.service.QuestionService;
import cn.edu.bjfu.igarden.service.UserService;
import cn.edu.bjfu.igarden.util.FileUtil;
import cn.edu.bjfu.igarden.util.UUIDUtils;
import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.Base64;

//import static com.sun.tools.doclint.Entity.image;

/**
 * Created by Cookie on 2019/2/25.
 */
@RestController
public class QuestionController {
    private QuestionService questionService;
    private UserService userService;
    private ExpertsService expertsService;
    public static final int PAGE_SIZE = 5;
    private static final String BASE_URL = "http://39.105.50.119:8080/question/";
    @Value("${web.questionImagePath}")
    private String path;
    @Autowired
    public QuestionController(QuestionService questionService,UserService userService,ExpertsService expertsService) {
        this.questionService = questionService;
        this.userService = userService;
        this.expertsService=expertsService;
    }

    @PostMapping(value = "/addQuestion")
    public Object addQuestion(Question question,ServletRequest request) {
        String imagePath = "";
        if(question.getImage().length()!=0||!question.getImage().equals("")){
            String questionImage=question.getImage();
            String base64Str[]= questionImage.split("#");

            for(int i=0;i<base64Str.length;i++){
                StringBuffer fileName = new StringBuffer();
                fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
                if(base64Str[i].contains("data:image/jpeg;base64,")){
                    base64Str[i] = base64Str[i].replace("data:image/jpeg;base64,", "").replaceAll("[\\s*\t\n\r]","+").replaceAll(",","");
                }
                else if(base64Str[i].contains("data:image/png;base64,")){
                    base64Str[i] = base64Str[i].replace("data:image/png;base64,", "").replaceAll("[\\s*\t\n\r]","+").replaceAll(",","");
                }else {
                    base64Str[i] = base64Str[i].replace("data:image/jpg;base64,", "").replaceAll("[\\s*\t\n\r]", "+").replaceAll(",", "");
                }
                imagePath =imagePath+BASE_URL + fileName.append(".jpg").toString()+"#";
                System.out.println("imagePath="+imagePath);
                File file = new File(path, fileName.toString());
                byte[] fileBytes = Base64.getDecoder().decode(base64Str[i]);
                try {
                    FileUtil.writeByteArrayToFile(file, fileBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            question.setImage(imagePath);
            System.out.println("imagePath="+imagePath);
        }
//        if(question.getPlace().length()!=0||!question.getPlace().equals("")){
//            question.setPlace(question.getPlace());
//        }
//
        JSONObject jsonObject = new JSONObject();
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
                User userInDataBase = userService.findById(login.getUserid());
                if (userInDataBase == null) {
                    jsonObject.put("code", "500");
                    jsonObject.put("message", "修改失败");
                } else {
                    //根据token获得login表中的userid
                    question.setUserid(userInDataBase.getId());
                    question.setQuestioner_username(userInDataBase.getUsername());
                    question.setQuestioner_name(userInDataBase.getName());
                    question.setQuestioner_avatar(userInDataBase.getAvatar());
                    question.setInvite(question.getInvite());
                    //将login表的userid放入question的useid
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "提问成功");
                    questionService.addQuestion(question);

                    if(!question.getInvite().equals("")){
                        System.out.println("+++++++++++++++++++33333");
                        Invite invite =new Invite();
                        List<String> expertId = Arrays.asList(question.getInvite().split("#"));
                        for(int i=0;i<expertId.size();i++){
                            invite.setExpertId((Integer)Integer.valueOf(expertId.get(i)).intValue());
                            User usernews = userService.findById((Integer)Integer.valueOf(expertId.get(i)).intValue());
                            usernews.setNews(usernews.getNews()+1);
                            userService.updateUser(usernews);
                            invite.setUserId(userInDataBase.getId());
                            invite.setQuestionId(question.getId());
                            expertsService.insertInviteRecord(invite);
                        }
                    }
                }
            }
        }
        return jsonObject;
    }

    /**
     * 用户 已读 信息
     * @param request
     * @param questionId
     */
    @PostMapping("/isRead")
    public void isRead(ServletRequest request,@RequestParam("questionId")int questionId) {
        JSONObject jsonObject = new JSONObject();
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
                //用户的expertId
                int expertId = login.getUserid();


                //根据expertId 与 questionId 将 isRead变为1
                userService.isRead(questionId, expertId);

                //根据expertId 得出 questionId 数组
                Invite[] questionIdS = userService.queryExpertId(expertId);

                //看用户的isread有多少个
                int isRead = 0;
                for (int i = 0; i < questionIdS.length; i++) {
                    if (questionIdS[i].getIsread() == 0) {
                        isRead++;
                    }
                }

                //根据experiId 查询出user
                User user = userService.findById(expertId);
                user.setNews(isRead);

                //将总数isRead的值 放入到user的new里面
                userService.updateUser(user);
                jsonObject.put("code","200");
                jsonObject.put("message","操作成功");

            }
        }
    }




    @PostMapping(value = "/addPlantQuestion")
    public Object addPlantQuestion(Question question,ServletRequest request) {
        String imagePath = "";
//        System.out.println(question.getImage().length()+"123");
        if(question.getImage().length()!=0||!question.getImage().equals("")){
            String questionImage=question.getImage();
            String base64Str[]= questionImage.split("#");
            for(int i=0;i<base64Str.length;i++){
                StringBuffer fileName = new StringBuffer();
                fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
                if(base64Str[i].contains("data:image/jpeg;base64,")){
                    base64Str[i] = base64Str[i].replace("data:image/jpeg;base64,", "").replaceAll("[\\s*\t\n\r]","+").replaceAll(",","");
                }
                else if(base64Str[i].contains("data:image/png;base64,")){
                    base64Str[i] = base64Str[i].replace("data:image/png;base64,", "").replaceAll("[\\s*\t\n\r]","+").replaceAll(",","");
                }else {
                    base64Str[i] = base64Str[i].replace("data:image/jpg;base64,", "").replaceAll("[\\s*\t\n\r]", "+").replaceAll(",", "");
                }
                imagePath =imagePath+BASE_URL + fileName.append(".jpg").toString()+"#";
                System.out.println("imagePath="+imagePath);
                File file = new File(path, fileName.toString());
                byte[] fileBytes = Base64.getDecoder().decode(base64Str[i]);
                try {
                    FileUtil.writeByteArrayToFile(file, fileBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            question.setImage(imagePath);
            System.out.println("imagePath="+imagePath);
        }
        JSONObject jsonObject = new JSONObject();
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
                User userInDataBase = userService.findById(login.getUserid());
                if (userInDataBase == null) {
                    jsonObject.put("code", "500");
                    jsonObject.put("message", "查询失败");
                } else {
                    //根据token获得login表中的userid
                    question.setUserid(userInDataBase.getId());
                    question.setQuestioner_username(userInDataBase.getUsername());
                    question.setQuestioner_name(userInDataBase.getName());
                    question.setQuestioner_avatar(userInDataBase.getAvatar());
                    question.setQtype(0);
                    //将login表的userid放入question的useid
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "提问成功");
                    jsonObject.put("data", question);
                    questionService.addPlantQuestion(question);
                    if(question.getInvite()!=null){
                        Invite invite =new Invite();
                        List<String> expertId = Arrays.asList(question.getInvite().split("#"));
                        for(int i=0;i<expertId.size();i++){
                            invite.setExpertId((Integer)Integer.valueOf(expertId.get(i)).intValue());
                            invite.setUserId(userInDataBase.getId());
                            invite.setQuestionId(question.getId());
                            expertsService.insertInviteRecord(invite);
                        }
                    }
                }
            }
        }
        return jsonObject;
    }
    @PostMapping(value = "/findMyQuestion")
    public Object findMyQuestion(ServletRequest request,@RequestParam("qtype")int qtype) {
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest)request;
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
                User userInDataBase = userService.findById(login.getUserid());
                if (userInDataBase == null) {
                    jsonObject.put("code", "500");
                    jsonObject.put("message", "修改失败");
                } else {
                    List<Question> questionList = questionService.findQuestionByUserId(userInDataBase.getId(),qtype);
                    for(int i =0;i<questionList.size();i++){
                        if(questionList.get(i).getImage()!=null){
                            String imagelist[]=questionList.get(i).getImage().split("#");
                            List images = Arrays.asList(imagelist);
                            questionList.get(i).setImageData(images);
                            questionList.get(i).setImage(questionList.get(i).getImage().split("#")[0]);
                        }
                    }
                    for(Question qu : questionList){
                        qu.setCreatetime(qu.getCreatetime().substring(0,19));
                    }

                    //根据token获得login表中的userid
                    //将login表的userid放入question的useid
                    if(questionList.size()>0){
                        jsonObject.put("code","200");
                        jsonObject.put("message","查询成功");
                        jsonObject.put("data",questionList);
                    }else {
                        jsonObject.put("code","201");
                        jsonObject.put("message","查询成功,但无我的问题数据");
                        jsonObject.put("data",questionList);
                    }
                }
            }
        }
        return jsonObject;
    }
    //    @PostMapping(value = "/findAll1")
//    public List<Question> itemsPage(){
//        return questionService.findAll();
//    }

    /**
     * jxy
     * @param request
     * @return
     */
    @PostMapping(value = "/checkMyUnReadQuestionList")
    public Object checkMyUnReadQuestionList(ServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest)request;
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
                User userInDataBase = userService.findById(login.getUserid());
                if (userInDataBase == null) {
                    jsonObject.put("code", "500");
                    jsonObject.put("message", "修改失败");
                } else {
                    List<Question> questionsInfoList = new ArrayList();
                    List<Invite> InviteInfo= userService.checkUnReadQuestionList(userInDataBase.getId());
                    for(int i=0;i<InviteInfo.size();i++){
                       Question questionsInfo= questionService.findQuestionById(InviteInfo.get(i).getQuestionId());
                       questionsInfoList.add(questionsInfo);
                        questionsInfoList.get(i).setCreatetime(questionsInfoList.get(i).getCreatetime().substring(0,19));
                    }

                    for(int i =0;i<questionsInfoList.size();i++){
                        if(questionsInfoList.get(i).getImage()!=null){
                            List<String> images = Arrays.asList(questionsInfoList.get(i).getImage().split("#"));
                            questionsInfoList.get(i).setImageData(images);
                            questionsInfoList.get(i).setImage(images.get(0));
                        }
                    }

                    Collections.reverse(questionsInfoList);



                    jsonObject.put("code", "200");
                    jsonObject.put("message", "找到列表");
                    jsonObject.put("data",questionsInfoList);
                }
            }
        }
        return jsonObject;
    }
    @PostMapping(value = "/checkMyUnReadQuestion")
    public Object checkMyUnReadQuestion(ServletRequest request,@RequestParam("questionid")int questionid) {
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest)request;
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
                User userInDataBase = userService.findById(login.getUserid());
                if (userInDataBase == null) {
                    jsonObject.put("code", "500");
                    jsonObject.put("message", "修改失败");
                } else {
                    Invite InviteInfo= userService.checkUnReadQuestion(questionid,userInDataBase.getId());
                    if(InviteInfo!=null){
                        jsonObject.put("code", "200");
                        Invite invite=new Invite();
                        invite.setId(InviteInfo.getId());
                        invite.setIsread(1);
                        userService.changeUnReadQuestion(invite);
                    }
                }
            }
        }
        return jsonObject;
    }
//    热门咨询列表
    @PostMapping(value = "/findHotQuestionByHitsAndQtype")
    public JSONObject findQuestionByHitsAndQtype(@RequestParam("qtype")int qtype) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        JSONObject question=questionService.findHotQuestionByHitsAndQtype(qtype);
//        if(question.size()==PAGE_SIZE){
        jsonObject.put("code","200");
        jsonObject.put("message","查询成功");
        jsonObject.put("data",question.get("data"));
        jsonObject.put("size",question.get("size"));
//        }else if(question.size()>0&&question.size()<PAGE_SIZE){
//            List<Question> questionLastPage=questionService.findQuestionByHitsAndQtype(qtype,currentPage);
//            jsonObject.put("code","201");
//            jsonObject.put("message","查询成功,已到最后一页");
//            jsonObject.put("page",currentPage);
//            jsonObject.put("data",questionLastPage);
//        }else {
//            jsonObject.put("message","查询成功,但热门无数据,返回上一页");
//            jsonObject.put("page",currentPage-1);
//            jsonObject.put("data",questionLastPage);
//        }
        return jsonObject;
    }

    @PostMapping(value = "/findQuestionById")
    public Object questionInfo(@RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        Question questionInfo=questionService.findQuestionById(id);
        if(questionInfo==null){
            jsonObject.put("code", "400");
            jsonObject.put("message", "找不到问题！");
        }else{
            if(questionInfo.getImage()!=null){
                List<String> images = Arrays.asList(questionInfo.getImage().split("#"));
                questionInfo.setImageData(images);
            }
            questionInfo.setCreatetime(questionInfo.getCreatetime().substring(0,19));
            questionInfo.setHits(questionInfo.getHits()+1);
            questionService.QuestionHits(questionInfo);
            if(questionInfo.getAccept_status()==0) {
                jsonObject.put("code", "200");
                jsonObject.put("message", "未采纳");
                jsonObject.put("data", questionInfo);
            }else{
                jsonObject.put("code", "201");
                jsonObject.put("message", "已采纳");
                jsonObject.put("data", questionInfo);
            }
        }
        return jsonObject;
    }
    //根据问题采纳状态获取列表
    @PostMapping(value = "/findQuestionListByAccept_Status")
    public JSONObject findQuestionListByAccept_Status(@RequestParam("currentPage")int currentPage,
                                                      @RequestParam("accept_status")int accept_status,
                                                      @RequestParam("qtype")int qtype) {

        JSONObject jsonObject = new JSONObject();
        JSONObject pages = questionService.findQuestionListByAccept_status(currentPage, accept_status, qtype);
        if (currentPage < 1) {
            currentPage = 1;
            jsonObject.put("code", "200");
            jsonObject.put("message", "查询成功");
            jsonObject.put("page", currentPage);
            jsonObject.put("totalNum", pages.get("totalNum"));
            jsonObject.put("data", pages.get("data"));

        } else {
            jsonObject.put("code", "200");
            jsonObject.put("message", "查询成功");
            jsonObject.put("page", currentPage);
            jsonObject.put("totalNum", pages.get("totalNum"));
            jsonObject.put("data", pages.get("data"));

//            jsonObject.put("nextPage",pages.get("nextPage"));
//            jsonObject.put("totalNum",questionService.findQuestionListByStatusTotalNum(status,qtype));
//        jsonObject.put(questionService.findQuestionListByStatus(currentPage,status,qtype).get());

        }
        return jsonObject;
    }
    //根据问题解答状态获取列表   status:0 该问题未被回答, 1 该问题已被解答
    @PostMapping(value = "/findQuestionListByStatus")
    public JSONObject findQuestionListByStatus(@RequestParam("currentPage")int currentPage,
                                               @RequestParam("status")int status,
                                               @RequestParam("qtype")int qtype) {
        JSONObject jsonObject = new JSONObject();


        JSONObject pages = questionService.findQuestionListByStatus(currentPage, status, qtype);
//        List<Question> questionList = questionService.findQuestionByUserId(userInDataBase.getId(),qtype);
//        for(int i =0;i<questionList.size();i++){
//            if(questionList.get(i).getImage()!=null){
////                            questionList.get(i).setImage(questionList.get(i).getImage().split("#")[0]);
//                List<String> images = Arrays.asList(questionList.get(i).getImage().split("#"));
//                questionList.get(i).setImageData(images);
//            }
//        }


        if (currentPage < 1) {
            currentPage = 1;
            jsonObject.put("code", "200");
            jsonObject.put("message", "查询成功");
            jsonObject.put("page", currentPage);
            jsonObject.put("totalNum", pages.get("totalNum"));
            jsonObject.put("data", pages.get("data"));
        } else {
            jsonObject.put("code", "200");
            jsonObject.put("message", "查询成功");
            jsonObject.put("page", currentPage);
            jsonObject.put("totalNum", pages.get("totalNum"));
            jsonObject.put("data", pages.get("data"));
        }
        return jsonObject;
    }
    @PostMapping(value = "/findQuestionBySolrId")
    public Object questionSolrInfo(@RequestParam("id")int id) throws Exception{
        JSONObject jsonObject = new JSONObject();
        Question questionInfo=questionService.queryById(id);
        if(questionInfo==null){
            jsonObject.put("code", "400");
            jsonObject.put("message", "找不到问题！");
        }else{
            if(questionInfo.getImage()!=null){
                List<String> solrimages = Arrays.asList(questionInfo.getImage().split("#"));
                questionInfo.setImageData(solrimages);
            }
            if(questionInfo.getAccept_status()==0) {
                jsonObject.put("code", "200");
                jsonObject.put("message", "未采纳");
                jsonObject.put("data", questionInfo);
            }else{
                jsonObject.put("code", "201");
                jsonObject.put("message", "已采纳");
                jsonObject.put("data", questionInfo);
            }
        }
        return jsonObject;
    }
}
