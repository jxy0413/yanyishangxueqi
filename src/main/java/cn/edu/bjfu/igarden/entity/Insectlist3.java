package cn.edu.bjfu.igarden.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yxy on 2019/1/24.
 */

//使用jpa注解配置映射关系
//    是一个实体类
//    和哪个数据表对应
@Entity
@Table(name = "insectlist3")
public class Insectlist3 {

    private static final long serialVersionUID= -3039703447657705408L;

    @Id   //这是一个主键
    @GeneratedValue   //主键自增
    private int id;

    private String kind2;

    private String kind3;

    private int count;

    public String getKind3() {
        return kind3;
    }

    public void setKind3(String kind3) {
        this.kind3 = kind3;
    }

    @Override
    public String toString() {
        return "Insectlist3{" +
                "id=" + id +
                ", kind2='" + kind2 + '\'' +
                ", kind3='" + kind3 + '\'' +
                ", count=" + count +
                '}';
    }

    public String getKind2() {
        return kind2;
    }

    public void setKind2(String kind2) {
        this.kind2 = kind2;
    }

    public Insectlist3() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
