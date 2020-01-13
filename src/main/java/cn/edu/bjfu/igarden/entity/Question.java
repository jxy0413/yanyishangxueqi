package cn.edu.bjfu.igarden.entity;


import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;
import java.util.List;

/**
 * Created by Cookie on 2019/2/22.
 */
public class Question {
    @Field
    private int id;
    @Field
    private String question_uniquekey;
    @Field
    private String title;
    @Field
    private String questiontitle;
    @Field
    private String questioncontent;
    @Field
    private String invite;
    @Field
    private String content;
    @Field
    private String image;
    @Field
    private String plant_type;
    @Field
    private String plant_name;
    @Field
    private String  answertype;
    @Field
    private int  qtype;
    @Field
    private int status;
    @Field
    private int hits;
    @Field
    private int accept_status;
    @Field
    private String bp;
    @Field
    private String createtime;
    @Field
    private String updatetime;
    @Field
    private int deletetime;
    @Field
    private int userid;
    @Field
    private String questioner_username;
    @Field
    private String questioner_name;
    @Field
    private String questioner_avatar;
    @Field
    private String place;

    private List<String> imageData;

    public String getQuestioner_name() {
        return questioner_name;
    }

    public void setQuestioner_name(String questioner_name) {
        this.questioner_name = questioner_name;
    }

    public String getQuestiontitle() {
        return questiontitle;
    }

    public void setQuestiontitle(String questiontitle) {
        this.questiontitle = questiontitle;
    }

    public String getQuestioncontent() {
        return questioncontent;
    }

    public void setQuestioncontent(String questioncontent) {
        this.questioncontent = questioncontent;
    }

    public String getQuestioner_avatar() {
        return questioner_avatar;
    }

    public void setQuestioner_avatar(String questioner_avatar) {
        this.questioner_avatar = questioner_avatar;
    }

    public String getQuestioner_username() {
        return questioner_username;
    }

    public void setQuestioner_username(String questioner_username) {
        this.questioner_username = questioner_username;
    }

    public String getPlant_type() {
        return plant_type;
    }

    public void setPlant_type(String plant_type) {
        this.plant_type = plant_type;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public int getAccept_status() {
        return accept_status;
    }

    public void setAccept_status(int accept_status) {
        this.accept_status = accept_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_uniquekey() {
        return question_uniquekey;
    }

    public void setQuestion_uniquekey(String question_uniquekey) {
        this.question_uniquekey = question_uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAnswertype() {
        return answertype;
    }

    public void setAnswertype(String answertype) {
        this.answertype = answertype;
    }

    public int  getQtype() {
        return qtype;
    }

    public void setQtype(int  qtype) {
        this.qtype = qtype;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }
    public void hitsPlus() {
        this.hits++;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(int deletetime) {
        this.deletetime = deletetime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public List<String> getImageData() {
        return imageData;
    }

    public void setImageData(List<String> imageData) {
        this.imageData = imageData;
    }
    public String getPlace() { return place; }

    public void setPlace(String place) { this.place = place; }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question_uniquekey='" + question_uniquekey + '\'' +
                ", title='" + title + '\'' +
                ", questiontitle='" + questiontitle + '\'' +
                ", questioncontent='" + questioncontent + '\'' +
                ", invite='" + invite + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", plant_type='" + plant_type + '\'' +
                ", plant_name='" + plant_name + '\'' +
                ", answertype='" + answertype + '\'' +
                ", qtype=" + qtype +
                ", status=" + status +
                ", hits=" + hits +
                ", accept_status=" + accept_status +
                ", bp='" + bp + '\'' +
                ", createtime=" + createtime +
                ", updatetime='" + updatetime + '\'' +
                ", deletetime=" + deletetime +
                ", userid=" + userid +
                ", questioner_username='" + questioner_username + '\'' +
                ", questioner_name='" + questioner_name + '\'' +
                ", questioner_avatar='" + questioner_avatar + '\'' +
                ", place='" + place + '\'' +
                ", imageData=" + imageData +
                '}';
    }
}
