package cn.edu.bjfu.igarden.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yxy on 2019/2/12.
 */
@Entity
@Table(name = "chinayuanlinlistqiaoguan")
public class Chinayuanlinlistqiaoguan {
    @Id
    @GeneratedValue
    private int id;
    private String zclass;
    private String zbc;
    private String zname;
    private String timenow;
    private int readtimes;
    private String sourcefrom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZclass() {
        return zclass;
    }

    public void setZclass(String zclass) {
        this.zclass = zclass;
    }

    public String getZbc() {
        return zbc;
    }

    public void setZbc(String zbc) {
        this.zbc = zbc;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getTimenow() {
        return timenow;
    }

    public void setTimenow(String timenow) {
        this.timenow = timenow;
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

    @Override
    public String toString() {
        return "Chinayuanlinlistqiaoguan{" +
                "id=" + id +
                ", zclass='" + zclass + '\'' +
                ", zbc='" + zbc + '\'' +
                ", zname='" + zname + '\'' +
                ", timenow='" + timenow + '\'' +
                ", readtimes='" + readtimes + '\'' +
                ", sourcefrom='" + sourcefrom + '\'' +
                '}';
    }
}
