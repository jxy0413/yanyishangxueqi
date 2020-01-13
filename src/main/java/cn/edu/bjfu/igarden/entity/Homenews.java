package cn.edu.bjfu.igarden.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yxy on 2019/4/4.
 */
@Entity
@Table(name = "homenews")
public class Homenews {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String fabutime;
    private String content;
    private String img;
    private String sourcefrom;

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

    public String getFabutime() {
        return fabutime;
    }

    public void setFabutime(String fabutime) {
        this.fabutime = fabutime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSourcefrom() {
        return sourcefrom;
    }

    public void setSourcefrom(String sourcefrom) {
        this.sourcefrom = sourcefrom;
    }

    @Override
    public String toString() {
        return "Homenews{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fabutime='" + fabutime + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", sourcefrom='" + sourcefrom + '\'' +
                '}';
    }
}
