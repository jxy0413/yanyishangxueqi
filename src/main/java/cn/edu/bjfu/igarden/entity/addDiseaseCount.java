package cn.edu.bjfu.igarden.entity;

import java.util.Date;

/**
 * Created by jxy on 2019/11/28.
 */
public class addDiseaseCount {
    private int id;
    private int type;
    private String diseaseName;
    private String diseaseTime;
    private String address; //区域
    private String location;//经纬度

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDiseaseTime() {
        return diseaseTime;
    }

    public void setDiseaseTime(String diseaseTime) {
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

    public addDiseaseCount() {
    }

    @Override
    public String toString() {
        return "addDiseaseCount{" +
                "id=" + id +
                ", type=" + type +
                ", diseaseName='" + diseaseName + '\'' +
                ", diseaseTime='" + diseaseTime + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public addDiseaseCount(int id, int type, String diseaseName, String diseaseTime, String address, String location) {
        this.id = id;
        this.type = type;
        this.diseaseName = diseaseName;
        this.diseaseTime = diseaseTime;
        this.address = address;
        this.location = location;
    }


}
