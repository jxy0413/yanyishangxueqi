package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.dao.AnswerMapper;
import cn.edu.bjfu.igarden.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cookie on 2019/2/27.
 */
@Service
public class AnswerService {
    private AnswerMapper answerMapper;
    @Autowired
    public AnswerService(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }
    public List<Answer> findAnswerByUserId(int id){
        Answer answer=new Answer();
        answer.setAnswerid(id);
        return answerMapper.findAnswerByUserId(answer);
    }
    public List<Answer> findQuestionListofAnswerByUserIdAndQtype(int answerid,int qtype){
        Answer answer=new Answer();
        answer.setAnswerid(answerid);
        answer.setQtype(qtype);
        return answerMapper.findQuestionListofAnswerByUserIdAndQtype(answer);
    }
    public List<Answer> findAnswerByQuestionId(int id){
        Answer answer=new Answer();
        answer.setQuestionid(id);
        return answerMapper.findAnswerByQuestionId(answer);
    }
    public Answer findAnswerById(int id){
        Answer answer =new Answer();
        answer.setId(id);
        return answerMapper.findAnswerById(answer);
    }
    public List<Answer> findAnswerByUserIdAndQuestionId(int uid,int qid){
        Answer answer=new Answer();
        answer.setQuestionid(qid);
        answer.setAnswerid(uid);
        return answerMapper.findAnswerByUserIdAndQuestionId(answer);
    }
    public int addAnswerByUerId(Answer answer){
        answer.setContent(answer.getContent());
        answer.setUp(answer.getUp());
        answer.setDislike(answer.getDislike());
        return answerMapper.addAnswerByUserId(answer);
    }
    public int changeAnswerAdopt(Answer answer){
        return answerMapper.changeAnswerAdopt(answer);
    }

    public int updateAnswerLikeTimes(Answer answer){
        return answerMapper.updateAnswerLikeTimes(answer);
    }

}
