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
@Table(name = "insectlist1")
public class Insectlist1 {

    private static final long serialVersionUID= -3039703447657705408L;

    @Id   //这是一个主键
    @GeneratedValue   //主键自增
    private int id;

    private String kind1;

    private int count;


    public Insectlist1() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKind1() {
        return kind1;
    }

    public void setKind1(String kind1) {
        this.kind1 = kind1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Insectlist1{" +
                "id=" + id +
                ", kind1='" + kind1 + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
