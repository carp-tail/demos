package org.example;

import org.example.pojo.Department;
import org.example.pojo.Employee;
import org.example.pojo.Role;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create at 16:53 2021/7/26
 */
public class Enums {

    public enum EmployeeEnum{
        ZHANGSAN("张三", 30, DepartmentEnum.BU1.department, Arrays.asList(RoleEnum.CTO.role, RoleEnum.CFO.role), "这个人是张三..."),
        LISI("李四", 35, DepartmentEnum.BU2.department, Arrays.asList(RoleEnum.CMO.role), "这个人是李四...");

        private Employee employee;

        EmployeeEnum(String name, int age, Department department, List<Role> roleList, String desc){
            this.employee = new Employee(name, age, department, roleList, desc);
        }

        public Employee getEmployee() {
            return employee;
        }
    }

    public enum DepartmentEnum{
        BU1("部门1", "这是部门1..."),
        BU2("部门2", "这是部门2..."),
        BU3("部门3", "这是部门3...");

        private Department department;

        DepartmentEnum(String name, String desc){
            this.department = new Department(name, desc);
        }
    }

    public enum RoleEnum{
        CTO("首席执行官", "管技术的..."),
        CFO("首席财务官", "管钱的..."),
        CMO("市场总监", "管运营的...");

        private Role role;

        RoleEnum(String name, String desc) {
            this.role = new Role(name, desc);
        }

        public Role getRole() {
            return role;
        }
    }
}
