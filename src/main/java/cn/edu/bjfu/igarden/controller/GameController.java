package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.entity.BaseEntity;
import cn.edu.bjfu.igarden.entity.GameTable;
import cn.edu.bjfu.igarden.model.GameImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    GameImpl gameImpl;

    /**
     * 自动生成题库信息
     *
     * @return 返回状态
     */
    @GetMapping(value = "/saveQuestion")
    public String saveQuestion() {
        gameImpl.saveQuestions();
        return "success";
    }

    /**
     * 分页获取题目id
     *
     * @param page 页数
     * @return 返回题目id列表
     */
    @GetMapping(value = "/getQuestionIds")
    public BaseEntity<List<Integer>> getQuestionIds(@RequestParam(value = "page") int page) {
        BaseEntity<List<Integer>> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(gameImpl.getQuestionIds(page));
        return baseEntity;
    }

    /**
     * 根据id获取题目信息
     *
     * @param id id
     * @return 返回题目信息
     */
    @GetMapping(value = "/getQuestionById")
    public BaseEntity<GameTable> getQuestionById(@RequestParam(value = "id") int id) {
        BaseEntity<GameTable> baseEntity = new BaseEntity<>();
        baseEntity.setCode(200);
        baseEntity.setMessage("success");
        baseEntity.setData(gameImpl.getQuestionById(id));
        return baseEntity;
    }
}
