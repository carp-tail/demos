package org.example.pojo;

import java.io.Serializable;

/**
 * @Author: guaniu
 * @Description: 角色Pojo
 * @Date: Create at 16:40 2021/7/26
 */
public class Role implements Serializable {

    private final static long serialVersionUID = 123L;

    //角色名称
    private String name;

    //描述
    private transient String desc;

    public Role(String name, String desc) {
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
        return "Role{" +
                "name = '" + name + '\'' +
                ", desc = '" + desc + '\'' +
                '}';
    }
}
