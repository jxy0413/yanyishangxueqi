package cn.edu.bjfu.igarden.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yxy on 2019/5/7.
 */
@Table(name = "alert")
@Entity
public class Alert {
    @Id
    @GeneratedValue
    private int id;
    private int  reporterid;
    private String  reportername;
    private String  reporterphone;
    private String  place;
    private String  imgs;
    private String  title;
    private String  content;
    private String  reporttime;
    private int leixing; //类型 0代表是病虫害端 1代表植物端
    private int type; //0 代表没有解决 1 代表解决了 2代表

    public int getLeixing() {
        return leixing;
    }

    public void setLeixing(int leixing) {
        this.leixing = leixing;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReporterid() {
        return reporterid;
    }

    public void setReporterid(int reporterid) {
        this.reporterid = reporterid;
    }

    public String getReportername() {
        return reportername;
    }

    public void setReportername(String reportername) {
        this.reportername = reportername;
    }

    public String getReporterphone() {
        return reporterphone;
    }

    public void setReporterphone(String reporterphone) {
        this.reporterphone = reporterphone;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
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

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", reporterid=" + reporterid +
                ", reportername='" + reportername + '\'' +
                ", reporterphone='" + reporterphone + '\'' +
                ", place='" + place + '\'' +
                ", imgs='" + imgs + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", reporttime='" + reporttime + '\'' +
                ", type=" + type +
                '}';
    }
}
