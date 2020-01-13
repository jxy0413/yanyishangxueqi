package cn.edu.bjfu.igarden.model;

import cn.edu.bjfu.igarden.dao.DiseaseOptionRepository;
import cn.edu.bjfu.igarden.dao.DiseaseQuestionRepository;
import cn.edu.bjfu.igarden.dao.DiseaseRepository;
import cn.edu.bjfu.igarden.entity.DiseaseOptionTable;
import cn.edu.bjfu.igarden.entity.DiseaseQuestion;
import cn.edu.bjfu.igarden.entity.DiseaseQuestionTable;
import cn.edu.bjfu.igarden.entity.DiseaseTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiseaseReasoningImpl {
    @Autowired
    DiseaseQuestionRepository diseaseQuestionRepository;
    @Autowired
    DiseaseOptionRepository diseaseOptionRepository;
    @Autowired
    DiseaseRepository diseaseRepository;

    private DiseaseQuestionTable getFirstQuestion(int plantId, int type) {
        return diseaseQuestionRepository.findByPlantIdAndDiseaseTypeAndQuestionSequenceAndDeleteTime(plantId, type, 1, 0);
    }

    private List<DiseaseOptionTable> getOptions(int questionId) {
        return diseaseOptionRepository.findByQuestionIdAndDeleteTime(questionId, 0);
    }

    private DiseaseQuestionTable getQuestion(int id) {
        return diseaseQuestionRepository.findByIdAndDeleteTime(id, 0);
    }

    private DiseaseQuestion getAllByQuestion(DiseaseQuestionTable question) {
        List<DiseaseOptionTable> options = getOptions(question.getId());
        DiseaseQuestion diseaseQuestion = new DiseaseQuestion();
        diseaseQuestion.setId(question.getId());
        diseaseQuestion.setQuestion(question.getQuestionName());
        List<DiseaseQuestion.Option> list = new ArrayList<>();
        for (DiseaseOptionTable option : options) {
            DiseaseQuestion.Option mOption = new DiseaseQuestion.Option();
            mOption.setId(option.getId());
            mOption.setAnswer(option.getOptionDescription());
            mOption.setImageUrl(option.getOptionImage());
            mOption.setNextQuestionId(option.getNextQuestionId());
            list.add(mOption);
        }
        diseaseQuestion.setOptions(list);
        return diseaseQuestion;
    }

    public DiseaseQuestion getDiseaseFirstQuestion(int plantId, int type) {
        DiseaseQuestionTable question = getFirstQuestion(plantId, type);
        return getAllByQuestion(question);
    }

    public DiseaseQuestion getDiseaseQuestion(int id) {
        DiseaseQuestionTable question = getQuestion(id);
        return getAllByQuestion(question);
    }

    public DiseaseTable getDiseaseByOptionId(int optionId) {
        return diseaseRepository.findByDiseaseOptionRelAndDeleteTime(optionId, 0);
    }
}
