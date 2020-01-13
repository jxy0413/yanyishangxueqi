package cn.edu.bjfu.igarden.entity;

public class DiseaseTuili {
    private String china_disease;
    private String china_buwei;
    private String china_weihaizz;
    private int china_id;
    private String china_title;
    private String china_zhengzhuang;//地理位置
    private String china_xunhuan;
    private String china_yinsu;
    private String china_bingyuan;
    private String china_fangfa;

    public String getChina_disease() {
        return china_disease;
    }

    public void setChina_disease(String china_disease) {
        this.china_disease = china_disease;
    }

    public String getChina_buwei() {
        return china_buwei;
    }

    public void setChina_buwei(String china_buwei) {
        this.china_buwei = china_buwei;
    }

    public String getChina_weihaizz() {
        return china_weihaizz;
    }

    public void setChina_weihaizz(String china_weihaizz) {
        this.china_weihaizz = china_weihaizz;
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

    @Override
    public String toString() {
        return "DiseaseTuili{" +
                "china_disease='" + china_disease + '\'' +
                ", china_buwei='" + china_buwei + '\'' +
                ", china_weihaizz='" + china_weihaizz + '\'' +
                ", china_zhengzhuang='" + china_zhengzhuang + '\'' +
                '}';
    }
}
