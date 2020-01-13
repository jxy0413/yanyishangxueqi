package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.Answer;
import cn.edu.bjfu.igarden.entity.Question;
import cn.edu.bjfu.igarden.service.AnswerService;
import cn.edu.bjfu.igarden.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by JXY on 2019/7/31.
 */
@Controller
public class ShareController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;


    @RequestMapping(value = "/shareWechat", method = RequestMethod.GET)
    public String hello1(Model model,int questionid){
        Question question = questionService.findQuestionById(questionid);
        System.out.println(question);
        List<Answer> answer = answerService.findAnswerByQuestionId(questionid);
        System.out.println(answer);
        model.addAttribute("question",question);
        model.addAttribute("answer",answer);
        return "hello";
    }


}
