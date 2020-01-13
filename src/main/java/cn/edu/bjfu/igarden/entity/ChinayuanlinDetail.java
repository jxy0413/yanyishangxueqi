package cn.edu.bjfu.igarden.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yxy on 2019/2/12.
 */
@Entity
@Table(name = "chinayuanlindetail")
public class ChinayuanlinDetail {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String dtree;
    private String disease;
    private String kind1;
    private String kind2;
    private String kind3;
    private String Englishname;
    private String othername;
    private String introduce;
    private String imgs;
    private String zhengzhuang;
    private String bingyuan;
    private String xunhuan;
    private String yinsu;
    private String tezheng;
    private String xixing;
    private String fangfa;
    private int readtimes;
    private String sourcefrom;
    private String timenow;
    private double w ;       //相似度(只在推荐时调用)

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public String getDisease() { return disease; }

    public void setDisease(String disease) { this.disease = disease; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDtree() {
        return dtree;
    }

    public void setDtree(String dtree) {
        this.dtree = dtree;
    }

    public String getKind1() {
        return kind1;
    }

    public void setKind1(String kind1) {
        this.kind1 = kind1;
    }

    public String getKind2() {
        return kind2;
    }

    public void setKind2(String kind2) {
        this.kind2 = kind2;
    }

    public String getKind3() {
        return kind3;
    }

    public void setKind3(String kind3) {
        this.kind3 = kind3;
    }

    public String getEnglishname() {
        return Englishname;
    }

    public void setEnglishname(String englishname) {
        Englishname = englishname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getZhengzhuang() {
        return zhengzhuang;
    }

    public void setZhengzhuang(String zhengzhuang) {
        this.zhengzhuang = zhengzhuang;
    }

    public String getBingyuan() {
        return bingyuan;
    }

    public void setBingyuan(String bingyuan) {
        this.bingyuan = bingyuan;
    }

    public String getXunhuan() {
        return xunhuan;
    }

    public void setXunhuan(String xunhuan) {
        this.xunhuan = xunhuan;
    }

    public String getYinsu() {
        return yinsu;
    }

    public void setYinsu(String yinsu) {
        this.yinsu = yinsu;
    }

    public String getTezheng() {
        return tezheng;
    }

    public void setTezheng(String tezheng) {
        this.tezheng = tezheng;
    }

    public String getXixing() {
        return xixing;
    }

    public void setXixing(String xixing) {
        this.xixing = xixing;
    }

    public String getFangfa() {
        return fangfa;
    }

    public void setFangfa(String fangfa) {
        this.fangfa = fangfa;
    }

    public int getReadtimes() {
        return readtimes;
    }

    public void setReadtimes(int readtimes) {
        this.readtimes = readtimes;
    }

    public String getSourcefrom() {
        return sourcefrom;
    }

    public void setSourcefrom(String sourcefrom) {
        this.sourcefrom = sourcefrom;
    }

    public String getTimenow() {
        return timenow;
    }

    public void setTimenow(String timenow) {
        this.timenow = timenow;
    }

    @Override
    public String toString() {
        return "ChinayuanlinDetail{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", w=" + w +
                '}';
    }
}
