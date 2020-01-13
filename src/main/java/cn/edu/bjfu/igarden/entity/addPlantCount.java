package cn.edu.bjfu.igarden.entity;

import java.util.Date;

/**
 * Created by jxy on 2019/12/2.
 */
public class addPlantCount {
    private int id;
    private int type;
    private String plantName;
    private Date diseaseTime;
    private String address; //区域
    private String location;//经纬度

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Date getDiseaseTime() {
        return diseaseTime;
    }

    public void setDiseaseTime(Date diseaseTime) {
        this.diseaseTime = diseaseTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "addPlantCount{" +
                "id=" + id +
                ", type=" + type +
                ", plantName='" + plantName + '\'' +
                ", diseaseTime=" + diseaseTime +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
