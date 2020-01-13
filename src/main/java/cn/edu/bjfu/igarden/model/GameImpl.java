package cn.edu.bjfu.igarden.model;

import cn.edu.bjfu.igarden.dao.GameRepository;
import cn.edu.bjfu.igarden.dao.PlantRepository;
import cn.edu.bjfu.igarden.entity.GameTable;
import cn.edu.bjfu.igarden.entity.PlantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class GameImpl {
    private static final int QUES_NUM = 50;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlantRepository plantRepository;

    private PlantTable getPlant(int... ids) {
        int quesRandId = (int) (Math.random() * 70 + 1);
        boolean flag = false;
        for (int id : ids) {
            if (quesRandId == id) {
                flag = true;
                break;
            }
        }
        if (flag) {
            return getPlant(ids);
        }
        PlantTable temp = plantRepository.findByIdAndDeleteTime(quesRandId, 0);
        if (temp == null || temp.getPlantImage().trim().equals("")) {
            return getPlant(ids);
        }
        return temp;
    }

    public void saveQuestions() {
        int id = 1;
        while (id < 1000) {
            PlantTable plantTable = plantRepository.findByIdAndDeleteTime(id, 0);
            if (plantTable == null || plantTable.getPlantImage().trim().equals("")) {
                id++;
                continue;
            }
            GameTable gameTable = new GameTable();
            gameTable.setQuestion("请选择图示的植物名称");
            gameTable.setQuestionImage(plantTable.getPlantImage().split("#")[0]);
            int rand = (int) (Math.random() * 4);
            gameTable.setAnswer(rand);
            PlantTable[] plants = new PlantTable[3];
            switch (rand) {
                case 0:
                    gameTable.setOptionA(plantTable.getPlantName());
                    plants[0] = getPlant(id);
                    gameTable.setOptionB(plants[0].getPlantName());
                    plants[1] = getPlant(id, plants[0].getId());
                    gameTable.setOptionC(plants[1].getPlantName());
                    plants[2] = getPlant(id, plants[0].getId(), plants[1].getId());
                    gameTable.setOptionD(plants[2].getPlantName());
                    break;
                case 1:
                    gameTable.setOptionB(plantTable.getPlantName());
                    plants[0] = getPlant(id);
                    gameTable.setOptionA(plants[0].getPlantName());
                    plants[1] = getPlant(id, plants[0].getId());
                    gameTable.setOptionC(plants[1].getPlantName());
                    plants[2] = getPlant(id, plants[0].getId(), plants[1].getId());
                    gameTable.setOptionD(plants[2].getPlantName());
                    break;
                case 2:
                    gameTable.setOptionC(plantTable.getPlantName());
                    plants[0] = getPlant(id);
                    gameTable.setOptionB(plants[0].getPlantName());
                    plants[1] = getPlant(id, plants[0].getId());
                    gameTable.setOptionA(plants[1].getPlantName());
                    plants[2] = getPlant(id, plants[0].getId(), plants[1].getId());
                    gameTable.setOptionD(plants[2].getPlantName());
                    break;
                case 3:
                    gameTable.setOptionD(plantTable.getPlantName());
                    plants[0] = getPlant(id);
                    gameTable.setOptionB(plants[0].getPlantName());
                    plants[1] = getPlant(id, plants[0].getId());
                    gameTable.setOptionC(plants[1].getPlantName());
                    plants[2] = getPlant(id, plants[0].getId(), plants[1].getId());
                    gameTable.setOptionA(plants[2].getPlantName());
                    break;
                default:
                    gameTable.setOptionA(plantTable.getPlantName());
                    plants[0] = getPlant(id);
                    gameTable.setOptionB(plants[0].getPlantName());
                    plants[1] = getPlant(id, plants[0].getId());
                    gameTable.setOptionC(plants[1].getPlantName());
                    plants[2] = getPlant(id, plants[0].getId(), plants[1].getId());
                    gameTable.setOptionD(plants[2].getPlantName());
                    break;
            }

            gameTable.setCreateTime(System.currentTimeMillis() / 1000);
            gameTable.setUpdateTime(System.currentTimeMillis() / 1000);
            gameRepository.save(gameTable);
            id++;
        }
    }

    public List<Integer> getQuestionIds(int page) {
        long count = gameRepository.countAllByDeleteTime(0);
        int baseNum = (count > page * QUES_NUM) ? QUES_NUM : (int) (count - (page - 1) * QUES_NUM);
        if (baseNum < 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = (page - 1) * QUES_NUM; i < (page - 1) * QUES_NUM + baseNum; i++) {
            list.add(i + 1);
        }
        Collections.shuffle(list);
        return list;
    }

    public GameTable getQuestionById(int id) {
        return gameRepository.findByIdAndDeleteTime(id, 0);
    }
}
