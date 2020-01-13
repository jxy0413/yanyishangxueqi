package cn.edu.bjfu.igarden.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yxy on 2019/2/12.
 */
@Entity
@Table(name = "chinayuanlinlist1")
public class Chinayuanlinlist1 {
    @Id
    @GeneratedValue
    private int id;
    private String zclass;
    private int count;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Chinayuanlinlist1{" +
                "id=" + id +
                ", zclass='" + zclass + '\'' +
                ", count=" + count +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZclass() {
        return zclass;
    }

    public void setZclass(String zclass) {
        this.zclass = zclass;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
