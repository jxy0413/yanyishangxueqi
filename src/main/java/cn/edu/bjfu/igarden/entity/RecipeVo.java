package cn.edu.bjfu.igarden.entity;

/**
 * Created by sunyang on 2016/12/8.
 */
public class RecipeVo {
    private int 菜谱ID;//菜谱id
    private int typeif;//类型号
    private String name;//名字
    private String zuofa;//做法
    private String texing;//特性
    private String tishi;//提示
    private String tiaoliao;//调料
    private String yuanliao;//原料

    public int getId() {
        return 菜谱ID;
    }

    public void setId(int 菜谱ID) {
        this.菜谱ID = 菜谱ID;
    }

    public int getTypeif() {
        return typeif;
    }

    public void setTypeif(int typeif) {
        this.typeif = typeif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZuofa() {
        return zuofa;
    }

    public void setZuofa(String zuofa) {
        this.zuofa = zuofa;
    }

    public String getTexing() {
        return texing;
    }

    public void setTexing(String texing) {
        this.texing = texing;
    }

    public String getTishi() {
        return tishi;
    }

    public void setTishi(String tishi) {
        this.tishi = tishi;
    }

    public String getTiaoliao() {
        return tiaoliao;
    }

    public void setTiaoliao(String tiaoliao) {
        this.tiaoliao = tiaoliao;
    }

    public String getYuanliao() {
        return yuanliao;
    }

    public void setYuanliao(String yuanliao) {
        this.yuanliao = yuanliao;
    }
}
