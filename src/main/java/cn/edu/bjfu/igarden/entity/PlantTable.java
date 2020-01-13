package cn.edu.bjfu.igarden.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "plant")
public class PlantTable {
    @Id
    @NotNull
    @GeneratedValue
    private int id;
    private String plantName;
    private String plantAlias;
    private String plantLatinName;
    private String plantImage;
    private String qr;
    private String plant_number;
    private String devision;
    private String plantFamily;
    private String plantGenus;
    private String plantDescription;
    private String plantXgsc;
    private String plantJzgy;
    private String plantFbdq;
    private String plantYhjs;
    private String plantBxtz;
    private String plantHksj;
    private int hits;
    private long createTime;
    private long updateTime;
    private long deleteTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantAlias() {
        return plantAlias;
    }

    public void setPlantAlias(String plantAlias) {
        this.plantAlias = plantAlias;
    }

    public String getPlantLatinName() {
        return plantLatinName;
    }

    public void setPlantLatinName(String plantLatinName) {
        this.plantLatinName = plantLatinName;
    }

    public String getPlantImage() {
        return plantImage;
    }

    public void setPlantImage(String plantImage) {
        this.plantImage = plantImage;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getPlantFamily() {
        return plantFamily;
    }

    public void setPlantFamily(String plantFamily) {
        this.plantFamily = plantFamily;
    }

    public String getDevision() {
        return devision;
    }

    public void setDevision(String devision) {
        this.devision = devision;
    }

    public String getPlantGenus() {
        return plantGenus;
    }

    public void setPlantGenus(String plantGenus) {
        this.plantGenus = plantGenus;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getPlantXgsc() {
        return plantXgsc;
    }

    public void setPlantXgsc(String plantXgsc) {
        this.plantXgsc = plantXgsc;
    }

    public String getPlantJzgy() {
        return plantJzgy;
    }

    public void setPlantJzgy(String plantJzgy) {
        this.plantJzgy = plantJzgy;
    }

    public String getPlantFbdq() {
        return plantFbdq;
    }

    public void setPlantFbdq(String plantFbdq) {
        this.plantFbdq = plantFbdq;
    }

    public String getPlantYhjs() {
        return plantYhjs;
    }

    public void setPlantYhjs(String plantYhjs) {
        this.plantYhjs = plantYhjs;
    }

    public String getPlantBxtz() {
        return plantBxtz;
    }

    public void setPlantBxtz(String plantBxtz) {
        this.plantBxtz = plantBxtz;
    }

    public String getPlantHksj() {
        return plantHksj;
    }

    public void setPlantHksj(String plantHksj) {
        this.plantHksj = plantHksj;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getPlant_number() {
        return plant_number;
    }

    public void setPlant_number(String plant_number) {
        this.plant_number = plant_number;
    }
}
