package org.example.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: guaniu
 * @Description: 用户Pojo
 * @Date: Create at 16:33 2021/7/26
 */
public class Employee implements Serializable {

    private final static long serialVersionUID = 123L;

    //姓名
    private String name;

    //年龄
    private int age;

    //所属部门
    private Department department;

    //所拥有的的角色
    private List<Role> roleList;

    //描述
    private transient String desc;

    public Employee(String name, int age, Department department, List<Role> roleList, String desc) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.roleList = roleList;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name = '" + name + '\'' +
                ", age = " + age +
                ", department = " + department +
                ", roleList = " + roleList +
                ", desc = '" + desc + '\'' +
                '}';
    }
}
