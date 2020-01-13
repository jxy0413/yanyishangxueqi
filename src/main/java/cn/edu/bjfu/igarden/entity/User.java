package cn.edu.bjfu.igarden.entity;


import java.util.Date;

/**
 * Created by Cookie on 2019/1/23.
 */
public class User {

//    role,username,password,mobile,email,avatar,sex,area,description,expertstag,expertstitle,expertsintro
    private Integer id;
    private String username;
    private String nickname;
    private String name;
    private Integer role;
    private String password;
    private String mobile;
    private String email;
    private String avatar;
    private String sex;
    private String area;
    private String description;
    private String expertstag;
    private String expertstitle;
    private String expertsintro;
    private String research;
    private String expertslevel;
    private String expertstype;
    private String expertsachievement;
    private String classification;
    private String oldPassword;
    private int news;


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }



    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public String getExpertslevel(){ return expertslevel;}

    public void setExpertslevel(String expertslevel){
        this.expertslevel=expertslevel;
    }

    public String getExpertstype(){ return expertstype;}

    public void setExpertstype(String expertstype){
        this.expertstype=expertstype;
    }

    public String getExpertsachievement(){ return expertsachievement;}

    public  void setExpertsachievement(String expertsachievement){
        this.expertsachievement=expertsachievement;
    }
    public String getExpertstag() {
        return expertstag;
    }

    public void setExpertstag(String expertstag) {
        this.expertstag = expertstag;
    }

    public String getExpertstitle() {
        return expertstitle;
    }

    public void setExpertstitle(String expertstitle) {
        this.expertstitle = expertstitle;
    }

    public String getExpertsintro() {
        return expertsintro;
    }

    public void setExpertsintro(String expertsintro) {
        this.expertsintro = expertsintro;
    }

    private int state;
    private int level;
    private int bp;
    private int hits;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNews() {
        return news;
    }

    public void setNews(int news) {
        this.news = news;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex='" + sex + '\'' +
                ", area='" + area + '\'' +
                ", description='" + description + '\'' +
                ", expertstag='" + expertstag + '\'' +
                ", expertstitle='" + expertstitle + '\'' +
                ", expertsintro='" + expertsintro + '\'' +
                ", research='" + research + '\'' +
                ", expertslevel='" + expertslevel + '\'' +
                ", expertstype='" + expertstype + '\'' +
                ", expertsachievement='" + expertsachievement + '\'' +
                ", classification='" + classification + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", news=" + news +
                ", state=" + state +
                ", level=" + level +
                ", bp=" + bp +
                ", hits=" + hits +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                '}';
    }
}
