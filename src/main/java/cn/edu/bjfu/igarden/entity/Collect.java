package cn.edu.bjfu.igarden.entity;

import java.util.Date;

/**
 * Created by yxy on 2019/3/7.
 */
public class Collect {
    private int id;
    private int collecttype;
    private int  userid;
    private int  plantid;
    private String plant_title;
    private int  insectid;
    private String insect_title;
    private int  chinayuanlinid;
    private String chinayuanlin_title;
    private int  questionplantid;
    private String questionplant_title;
    private int  questiondiseaseid;
    private String questiondisease_title;
    private Date collectitime;
    private String disease;

    public String getPlant_title() {
        return plant_title;
    }

    public void setPlant_title(String plant_title) {
        this.plant_title = plant_title;
    }

    public String getInsect_title() {
        return insect_title;
    }

    public void setInsect_title(String insect_title) {
        this.insect_title = insect_title;
    }

    public String getChinayuanlin_title() {
        return chinayuanlin_title;
    }

    public void setChinayuanlin_title(String chinayuanlin_title) {
        this.chinayuanlin_title = chinayuanlin_title;
    }

    public String getQuestionplant_title() {
        return questionplant_title;
    }

    public void setQuestionplant_title(String questionplant_title) {
        this.questionplant_title = questionplant_title;
    }

    public String getQuestiondisease_title() {
        return questiondisease_title;
    }

    public void setQuestiondisease_title(String questiondisease_title) {
        this.questiondisease_title = questiondisease_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCollecttype() {
        return collecttype;
    }

    public void setCollecttype(int collecttype) {
        this.collecttype = collecttype;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPlantid() {
        return plantid;
    }

    public void setPlantid(int plantid) {
        this.plantid = plantid;
    }

    public int getInsectid() {
        return insectid;
    }

    public void setInsectid(int insectid) {
        this.insectid = insectid;
    }

    public int getChinayuanlinid() {
        return chinayuanlinid;
    }

    public void setChinayuanlinid(int chinayuanlinid) {
        this.chinayuanlinid = chinayuanlinid;
    }

    public int getQuestionplantid() {
        return questionplantid;
    }

    public void setQuestionplantid(int questionplantid) {
        this.questionplantid = questionplantid;
    }

    public int getQuestiondiseaseid() {
        return questiondiseaseid;
    }

    public void setQuestiondiseaseid(int questiondiseaseid) {
        this.questiondiseaseid = questiondiseaseid;
    }

    public Date getCollectitime() {
        return collectitime;
    }

    public void setCollectitime(Date collectitime) {
        this.collectitime = collectitime;
    }


    public String getDisease() { return disease; }

    public void setDisease(String disease) { this.disease = disease; }

}
