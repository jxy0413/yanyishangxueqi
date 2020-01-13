package cn.edu.bjfu.igarden.entity;

/**
 * Created by Cookie on 2019/5/7.
 */
public class PlantFamilyTypeTable {
    private int id;
    private String family;
    private String genus;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }
}
