package cn.edu.bjfu.igarden.entity;

/**
 * Created by jxy on 2019/10/24.
 * 根据植物名字 查询出chinayuanlin_id
 */
public class sql_sort {
    private int id;
    private String plant_name;
    private String plant_type;
    private int chinayuanlin_id;

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

    public int getChinayuanlin_id() {
        return chinayuanlin_id;
    }

    public void setChinayuanlin_id(int chinayuanlin_id) {
        this.chinayuanlin_id = chinayuanlin_id;
    }

    public sql_sort() {
    }

    public sql_sort(int id, String plant_name, String plant_type, int chinayuanlin_id) {
        this.id = id;
        this.plant_name = plant_name;
        this.plant_type = plant_type;
        this.chinayuanlin_id = chinayuanlin_id;
    }

    @Override
    public String toString() {
        return "sql_sort{" +
                "id=" + id +
                ", plant_name='" + plant_name + '\'' +
                ", plant_type='" + plant_type + '\'' +
                ", chinayuanlin_id=" + chinayuanlin_id +
                '}';
    }
}
