package cn.edu.bjfu.igarden.entity;

import java.awt.*;
import java.util.List;

/**
 * Created by jxy on 2019/7/30.
 */
public class PlantDetail {
    private int id;
    private String plant_name;
    private String plant_alias;
    private String plant_latin_name;
    private String plant_image;
    private String plant_family;
    private String plant_genus;
    private String plant_description;
    private String plant_xgsc;
    private String plant_jzgy;
    private String plant_fbdq;
    private String plant_yhjs;
    private String plant_bxtz;
    private String plant_hksj;
    private int hits;
    private int create_time;
    private int update_time;
    private int delete_time;
    private String devision;
    private String plant_number;
    private int shibieCount; //识别次数
    double w;       //相似度(只在推荐时调用)

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public String getPlant_image() {
        return plant_image;
    }

    public void setPlant_image(String plant_image) {
        this.plant_image = plant_image;
    }

    public int getShibieCount() {
        return shibieCount;
    }

    public void setShibieCount(int shibieCount) {
        this.shibieCount = shibieCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getPlant_alias() {
        return plant_alias;
    }

    public void setPlant_alias(String plant_alias) {
        this.plant_alias = plant_alias;
    }

    public String getPlant_latin_name() {
        return plant_latin_name;
    }

    public void setPlant_latin_name(String plant_latin_name) {
        this.plant_latin_name = plant_latin_name;
    }


    public String getPlant_family() {
        return plant_family;
    }

    public void setPlant_family(String plant_family) {
        this.plant_family = plant_family;
    }

    public String getPlant_genus() {
        return plant_genus;
    }

    public void setPlant_genus(String plant_genus) {
        this.plant_genus = plant_genus;
    }

    public String getPlant_description() {
        return plant_description;
    }

    public void setPlant_description(String plant_description) {
        this.plant_description = plant_description;
    }

    public String getPlant_xgsc() {
        return plant_xgsc;
    }

    public void setPlant_xgsc(String plant_xgsc) {
        this.plant_xgsc = plant_xgsc;
    }

    public String getPlant_jzgy() {
        return plant_jzgy;
    }

    public void setPlant_jzgy(String plant_jzgy) {
        this.plant_jzgy = plant_jzgy;
    }

    public String getPlant_fbdq() {
        return plant_fbdq;
    }

    public void setPlant_fbdq(String plant_fbdq) {
        this.plant_fbdq = plant_fbdq;
    }

    public String getPlant_yhjs() {
        return plant_yhjs;
    }

    public void setPlant_yhjs(String plant_yhjs) {
        this.plant_yhjs = plant_yhjs;
    }

    public String getPlant_bxtz() {
        return plant_bxtz;
    }

    public void setPlant_bxtz(String plant_bxtz) {
        this.plant_bxtz = plant_bxtz;
    }

    public String getPlant_hksj() {
        return plant_hksj;
    }

    public void setPlant_hksj(String plant_hksj) {
        this.plant_hksj = plant_hksj;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public int getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(int delete_time) {
        this.delete_time = delete_time;
    }

    public String getDevision() {
        return devision;
    }

    public void setDevision(String devision) {
        this.devision = devision;
    }

    public String getPlant_number() {
        return plant_number;
    }

    public void setPlant_number(String plant_number) {
        this.plant_number = plant_number;
    }

    @Override
    public String toString() {
        return "PlantDetail{" +
                "id=" + id +
                ", plant_name='" + plant_name + '\'' +
                ", plant_alias='" + plant_alias + '\'' +
                ", plant_latin_name='" + plant_latin_name + '\'' +
                ", plant_image=" + plant_image +
                ", plant_family='" + plant_family + '\'' +
                ", plant_genus='" + plant_genus + '\'' +
                ", plant_description='" + plant_description + '\'' +
                ", plant_xgsc='" + plant_xgsc + '\'' +
                ", plant_jzgy='" + plant_jzgy + '\'' +
                ", plant_fbdq='" + plant_fbdq + '\'' +
                ", plant_yhjs='" + plant_yhjs + '\'' +
                ", plant_bxtz='" + plant_bxtz + '\'' +
                ", plant_hksj='" + plant_hksj + '\'' +
                ", hits=" + hits +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", delete_time=" + delete_time +
                ", devision='" + devision + '\'' +
                ", plant_number='" + plant_number + '\'' +
                '}';
    }
}
