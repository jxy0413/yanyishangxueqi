package cn.edu.bjfu.igarden.entity;

/**
 * Created by yxy on 2019/5/23.
 */
public class InsectSearchEntity {

    //    农业病虫害
    private int insect_id;
    private String insect_title;
    private String insect_othername;
    private String insect_introduce;
    private String insect_zhengzhuang;
    private String insect_yinsu;
    private String insect_bingyuan;
    private String insect_fangfa;

    private int china_id;
    private String china_disease;
    private String china_title;
    private String china_zhengzhuang;
    private String china_xunhuan;
    private String china_yinsu;
    private String china_bingyuan;
    private String china_fangfa;

    public int getInsect_id() {
        return insect_id;
    }

    public void setInsect_id(int insect_id) {
        this.insect_id = insect_id;
    }

    public String getInsect_title() {
        return insect_title;
    }

    public void setInsect_title(String insect_title) {
        this.insect_title = insect_title;
    }

    public String getInsect_othername() {
        return insect_othername;
    }

    public void setInsect_othername(String insect_othername) {
        this.insect_othername = insect_othername;
    }

    public String getInsect_introduce() {
        return insect_introduce;
    }

    public void setInsect_introduce(String insect_introduce) {
        this.insect_introduce = insect_introduce;
    }

    public String getInsect_zhengzhuang() {
        return insect_zhengzhuang;
    }

    public void setInsect_zhengzhuang(String insect_zhengzhuang) {
        this.insect_zhengzhuang = insect_zhengzhuang;
    }

    public String getInsect_yinsu() {
        return insect_yinsu;
    }

    public void setInsect_yinsu(String insect_yinsu) {
        this.insect_yinsu = insect_yinsu;
    }

    public String getInsect_bingyuan() {
        return insect_bingyuan;
    }

    public void setInsect_bingyuan(String insect_bingyuan) {
        this.insect_bingyuan = insect_bingyuan;
    }

    public String getInsect_fangfa() {
        return insect_fangfa;
    }

    public void setInsect_fangfa(String insect_fangfa) {
        this.insect_fangfa = insect_fangfa;
    }

    public int getChina_id() {
        return china_id;
    }

    public void setChina_id(int china_id) {
        this.china_id = china_id;
    }

    public String getChina_title() {
        return china_title;
    }

    public void setChina_title(String china_title) {
        this.china_title = china_title;
    }

    public String getChina_zhengzhuang() {
        return china_zhengzhuang;
    }

    public void setChina_zhengzhuang(String china_zhengzhuang) {
        this.china_zhengzhuang = china_zhengzhuang;
    }

    public String getChina_xunhuan() {
        return china_xunhuan;
    }

    public void setChina_xunhuan(String china_xunhuan) {
        this.china_xunhuan = china_xunhuan;
    }

    public String getChina_yinsu() {
        return china_yinsu;
    }

    public void setChina_yinsu(String china_yinsu) {
        this.china_yinsu = china_yinsu;
    }

    public String getChina_bingyuan() {
        return china_bingyuan;
    }

    public void setChina_bingyuan(String china_bingyuan) {
        this.china_bingyuan = china_bingyuan;
    }

    public String getChina_fangfa() {
        return china_fangfa;
    }

    public void setChina_fangfa(String china_fangfa) {
        this.china_fangfa = china_fangfa;
    }

    public String getChina_disease() {
        return china_disease;
    }

    public void setChina_disease(String china_disease) {
        this.china_disease = china_disease;
    }


    @Override
    public String toString() {
        return "InsectSearchEntity{" +
                "insect_id=" + insect_id +
                ", insect_title='" + insect_title + '\'' +
                ", insect_othername='" + insect_othername + '\'' +
                ", insect_introduce='" + insect_introduce + '\'' +
                ", insect_zhengzhuang='" + insect_zhengzhuang + '\'' +
                ", insect_yinsu='" + insect_yinsu + '\'' +
                ", insect_bingyuan='" + insect_bingyuan + '\'' +
                ", insect_fangfa='" + insect_fangfa + '\'' +
                ", china_id=" + china_id +
                ", china_title='" + china_title + '\'' +
                ", china_zhengzhuang='" + china_zhengzhuang + '\'' +
                ", china_xunhuan='" + china_xunhuan + '\'' +
                ", china_yinsu='" + china_yinsu + '\'' +
                ", china_bingyuan='" + china_bingyuan + '\'' +
                ", china_fangfa='" + china_fangfa + '\'' +
                '}';
    }
}