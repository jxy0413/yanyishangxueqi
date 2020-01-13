package cn.edu.bjfu.igarden.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Cookie on 2019/2/22.
 */
public class Answer {
    private int id;
    private int questionid;
    private int qtype;
    private int answerid;
    private int questionerid;
    private String content;
    private int up;
    private Date updateTime;
    private Date deleteTime;
    private String image;
    private String answer_username;
    private String answer_name;
    private String answer_avatar;
    private int dislike;
    private int adoptstatus;
    private String create_time;
    private String question_title;
    private List<String> imageData;

    public List<String> getImageData() {
        return imageData;
    }


    public void setImageData(List<String> imageData) {
        this.imageData = imageData;
    }

    public int getQtype() {
        return qtype;
    }

    public void setQtype(int qtype) {
        this.qtype = qtype;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getAnswer_username() {
        return answer_username;
    }

    public void setAnswer_username(String answer_username) {
        this.answer_username = answer_username;
    }

    public String getAnswer_name() {
        return answer_name;
    }

    public void setAnswer_name(String answer_name) {
        this.answer_name = answer_name;
    }

    public String getAnswer_avatar() {
        return answer_avatar;
    }

    public void setAnswer_avatar(String answer_avatar) {
        this.answer_avatar = answer_avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public int getAnswerid() {
        return answerid;
    }

    public void setAnswerid(int answerid) {
        this.answerid = answerid;
    }

    public int getQuestionerid() {
        return questionerid;
    }

    public void setQuestionerid(int questionerid) {
        this.questionerid = questionerid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getAdoptstatus() {
        return adoptstatus;
    }

    public void setAdoptstatus(int adoptstatus) {
        this.adoptstatus = adoptstatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionid=" + questionid +
                ", qtype=" + qtype +
                ", answerid=" + answerid +
                ", questionerid=" + questionerid +
                ", content='" + content + '\'' +
                ", up=" + up +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                ", image='" + image + '\'' +
                ", answer_username='" + answer_username + '\'' +
                ", answer_name='" + answer_name + '\'' +
                ", answer_avatar='" + answer_avatar + '\'' +
                ", dislike=" + dislike +
                ", adoptstatus=" + adoptstatus +
                ", create_time='" + create_time + '\'' +
                ", question_title='" + question_title + '\'' +
                ", imageData=" + imageData +
                '}';
    }
}
