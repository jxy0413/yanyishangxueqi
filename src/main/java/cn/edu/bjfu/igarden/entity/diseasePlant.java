package cn.edu.bjfu.igarden.entity;

/**
 * Created by jxy on 2019/10/24.
 */
public class diseasePlant {
    private int id;
    private String plant_name;
    private String plant_type;

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

    public String getPlant_type() {
        return plant_type;
    }

    public void setPlant_type(String plant_type) {
        this.plant_type = plant_type;
    }

    public diseasePlant(int id, String plant_name, String plant_type) {
        this.id = id;
        this.plant_name = plant_name;
        this.plant_type = plant_type;
    }

    public diseasePlant() {
    }

    @Override
    public String toString() {
        return "diseasePlant{" +
                "id=" + id +
                ", plant_name='" + plant_name + '\'' +
                ", plant_type='" + plant_type + '\'' +
                '}';
    }
}
