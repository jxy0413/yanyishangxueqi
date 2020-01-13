package cn.edu.bjfu.igarden.entity;

/**
 * Created by yxy on 2019/5/18.
 */
public class ImgClassifyResult {
    private int id;
    private int shibieid;
    private String score;
    private String bname;
    private String baike_url;
    private String image_url;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShibieid() {
        return shibieid;
    }

    public String getBname() {
        return bname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setShibieid(int shibieid) {
        this.shibieid = shibieid;
    }

    public String getBaike_url() {
        return baike_url;
    }

    public void setBaike_url(String baike_url) {
        this.baike_url = baike_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

