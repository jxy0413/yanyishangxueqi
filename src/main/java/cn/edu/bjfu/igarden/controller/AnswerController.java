package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.*;
import cn.edu.bjfu.igarden.service.AnswerService;
import cn.edu.bjfu.igarden.service.QuestionService;
import cn.edu.bjfu.igarden.service.UserService;
import cn.edu.bjfu.igarden.util.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * Created by Cookie on 2019/2/27.
 */
@RestController
public class AnswerController {
    private static final String BASE_URL = "http://39.105.50.119:8080/answer/";
    @Value("${web.answerImagePath}")
    private String path;

    private AnswerService answerService;
    private QuestionService questionService;
    private UserService userService;

    @Autowired
    public AnswerController(AnswerService answerService,QuestionService questionService,UserService userService){
        this.answerService=answerService;
        this.questionService=questionService;
        this.userService=userService;
    }
    @PostMapping(value = "/answerQuestion")
    public Object addAnswerById(Answer answer,ServletRequest request){
//        public Object addAnswerById(Answer answer,ServletRequest request,@RequestParam("questionerid") int questionerid,@RequestParam("questionid") int questionid){

        String imagePath = "";
        if(answer.getImage().length()!=0||!answer.getImage().equals("")){
            String questionImage=answer.getImage();
            String base64Str[]= questionImage.split("#");
            System.out.println(base64Str[0]);
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
            answer.setImage(imagePath);
            System.out.println("imagePath="+imagePath);
        }

        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest)request;
        String token = req.getHeader("token");
        if(token == null){
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        }else {
            Login loginInfo = userService.findUseridByToken(token);
            if (loginInfo == null) {
                jsonObject.put("code", "404");
                jsonObject.put("message", "找不到登录信息");
            } else {
                User user = userService.findById(loginInfo.getUserid());
                if (user != null) {
                    Question question = questionService.findQuestionById(answer.getQuestionid());
//                    1已解答 0未解答
                    question.setId(answer.getQuestionid());
                    question.setStatus(1);
                    questionService.changeQuestionStatus(question);
//                    answer.setQuestionid(questionid);
                    answer.setAnswerid(loginInfo.getUserid());
                    answer.setAnswer_username(user.getUsername());
                    answer.setAnswer_name(user.getName());
                    answer.setAnswer_avatar(user.getAvatar());
//                    answer.setQuestionerid(questionerid);
                    answer.setQuestion_title(question.getTitle());
                    answer.setImage(question.getImage());
                    answerService.addAnswerByUerId(answer);
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "回答成功");
                    jsonObject.put("data", answer);
                }
            }
        }
        return jsonObject;
    }
    
    @PostMapping(value = "/findQuestionListofAnswerByUserIdAndQtype")
    public Object findQuestionListofAnswerByUserIdAndQtype(ServletRequest request,@RequestParam("qtype")int qtype){
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest)request;
        String token = req.getHeader("token");
        if(token == null){
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        }else {
            Login loginInfo = userService.findUseridByToken(token);
            if (loginInfo == null) {
                jsonObject.put("code", "404");
                jsonObject.put("message", "找不到登录信息");
            } else {
                if (userService.findByUserId(loginInfo.getUserid()) != null) {
                    List<Answer> answerList = answerService.findQuestionListofAnswerByUserIdAndQtype(loginInfo.getUserid(),qtype);
                    for(int i= 0;i<answerList.size();i++)
                    {
                        String imagelist[]=answerList.get(i).getImage().split("#");
                        List images = Arrays.asList(imagelist);
                        answerList.get(i).setImageData(images);
                        answerList.get(i).setImage(imagelist[0]);
                    }
                    for (Answer an : answerList) {
                        an.setCreate_time(an.getCreate_time().substring(0, 19));
                    }
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "查询成功");
                    jsonObject.put("data", answerList);
                }
            }
        }
        return jsonObject;
    }
    
    @PostMapping(value = "/findAnswerByUserId")
    public Object findMyAnswer(ServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest req = (HttpServletRequest)request;
        String token = req.getHeader("token");
        if(token == null){
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        }else{
            Login loginInfo = userService.findUseridByToken(token);
            if(loginInfo==null){
                jsonObject.put("code", "404");
                jsonObject.put("message", "找不到登录信息");
            } else{

                if (userService.findByUserId(loginInfo.getUserid())!= null) {

                    List<Answer> answer=answerService.findAnswerByUserId(loginInfo.getUserid());
                    for(Answer an : answer){
                        an.setCreate_time(an.getCreate_time().substring(0,19));
                    }
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "查询成功");
                    jsonObject.put("data", answer);
                }
            }
        }
        return jsonObject;
    }
    @PostMapping(value = "/findAnswerByQuestionId")
    public Object findQuestionAnswer(@RequestParam("questionid") int questionid,ServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //获取请求头的TOKEN值
        HttpServletRequest req = (HttpServletRequest)request;
        String token = req.getHeader("token");
        if(token == null){
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        }else{
            Login login = userService.findUseridByToken(token);
            if(login == null){
                jsonObject.put("code", "404");
                jsonObject.put("message", "找不到登录信息");
            }else {
                //根据问题ID获取该问题详情
                Question question = questionService.findQuestionById(questionid);
                //根据问题ID获取回答列表
                List<Answer> answer = answerService.findAnswerByQuestionId(questionid);
                for(int i= 0;i<answer.size();i++)
                {
                    String imagelist[]=answer.get(i).getImage().split("#");
                    List images = Arrays.asList(imagelist);
                    answer.get(i).setImageData(images);
                }

                for(Answer an : answer){
                    an.setCreate_time(an.getCreate_time().substring(0,19));
                }
                //若问题id与登录时的用户ID相同则可以采纳问题
                if(question.getUserid()==login.getUserid()){
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "具有采纳权限");
                    jsonObject.put("data",answer);
                } else {
                    jsonObject.put("code", "202");
                    jsonObject.put("message", "不具有采纳权限");
                    jsonObject.put("data",answer);
                }
            }
        }
        return jsonObject;
    }
    @PostMapping(value = "/findAnswerByQuestionAndUserId")
    public Object findAnswerByQuestionAndUserId(@RequestParam("uid") int uid,@RequestParam("qid") int qid){
        JSONObject jsonObject = new JSONObject();
        //根据用户id与问题id获取
        List<Answer> answer = answerService.findAnswerByUserIdAndQuestionId(uid,qid);
        for(Answer an : answer){
            an.setCreate_time(an.getCreate_time().substring(0,19));
        }
        if(answer==null){
            jsonObject.put("code", "404");
            jsonObject.put("message", "用户回答的此问题不存在！");
        } else{
            jsonObject.put("code", "200");
            jsonObject.put("message", "查询成功");
            jsonObject.put("data", answer);
        }
        return jsonObject;
    }
    @PostMapping(value = "/updateAnswerAdopt")
    public Object updateAnswerAdopt(@RequestParam("adoptstatus") int adoptstatus,
                                    ServletRequest request,
                                    @RequestParam("answerid") int answerid,
                                    @RequestParam("questionid") int questionid){
        JSONObject jsonObject=new JSONObject();
        HttpServletRequest req = (HttpServletRequest)request;
        String token = req.getHeader("token");
        if(token == null){
            jsonObject.put("code", "405");
            jsonObject.put("message", "无token！");
        }else {
            Login loginInfo = userService.findUseridByToken(token);
            //获取login信息
            if (loginInfo == null) {
                jsonObject.put("code", "404");
                jsonObject.put("massage", "未找到信息");
            } else {
                //获取用户信息
                Question question=new Question();
                Answer answer = new Answer();
                question.setId(questionid);
                question.setAccept_status(adoptstatus);
                questionService.adoptQuestion(question);
                answer.setAdoptstatus(adoptstatus);
                answer.setId(answerid);
                answerService.changeAnswerAdopt(answer);
                jsonObject.put("code", "200");
                jsonObject.put("massage", "修改采纳状态成功");
                jsonObject.put("data", answer);
                return jsonObject;
            }
        }
        return jsonObject;
    }
    @PostMapping(value = "/addAnswerLikeTimes")
    public Object addAnswerLikeTimes(Answer answer){
        JSONObject jsonObject = new JSONObject();
        //根据用户id与问题id获取
       answerService.updateAnswerLikeTimes(answer);
            jsonObject.put("code", "200");
            jsonObject.put("message", "新增次数成功");
//            jsonObject.put("data", answer);
        return jsonObject;
    }


    @PostMapping(value = "/findAnswerById")
    public Object findAnswerById(@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        //根据用户id与问题id获取
        Answer answer = answerService.findAnswerById(id);
        if(answer==null){
            jsonObject.put("code", "404");
            jsonObject.put("message", "此回答不存在！");
        } else{
            jsonObject.put("code", "200");
            jsonObject.put("message", "查询成功");
            jsonObject.put("data", answer);
        }
        return jsonObject;
    }
}
