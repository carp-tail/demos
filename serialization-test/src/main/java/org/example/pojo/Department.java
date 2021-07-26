package org.example.pojo;

import java.io.Serializable;

/**
 * @Author: guaniu
 * @Description: 部门Pojo
 * @Date: Create at 16:36 2021/7/26
 */
public class Department implements Serializable {

    private final static long serialVersionUID = 123L;

    //部门名称
    private String name;

    //简介
    private transient String desc;

    public Department(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name = '" + name + '\'' +
                ", desc = '" + desc + '\'' +
                '}';
    }
}
