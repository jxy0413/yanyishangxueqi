package cn.edu.bjfu.igarden.dao;
import cn.edu.bjfu.igarden.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Cookie on 2019/2/25.
 */
@Mapper
public interface QuestionMapper {
    int addQuestion(Question question);
    int addPlantQuestion(Question question);
    List<Question> findQuestionByUserId(Question question);
    List<Question> findQuestionByQtype(Question question);
    List<Question> findHotQuestionByHitsAndQtype(Question question);
    List<Question> findAll();
    int updateQuestionUniquekey(Question question);
    Question findQuestionById(Question question);
    List<Question> findQuestionListByStatus(Question question);
    List<Question> findQuestionListByAcceptStatus(Question question);
    Page<Question> findQuestionByDeleteTime(long deleteTime, Pageable pageable);
    int adoptQuestion(Question question);
    int QuestionHits(Question question);
    int changeQuestionStatus(Question question);
    Question findQuestionStatusById(Question question);

}
