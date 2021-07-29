package org.example;

import org.example.pojo.Employee;
import org.example.pojo.EmployeeWithDiffId;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author: guaniu
 * @Description: 这个检验了一下，不同的Pojo类相同的serialVersionUID是否能够反序列化
 *              1.Java的Serializable接口实现是失败的
 * @Date: Create at 20:24 2021/7/26
 */
public class EmployeeWithDiffIdTest {
    public static void main(String[] args) throws Exception {
        Employee employee = Enums.EmployeeEnum.ZHANGSAN.getEmployee();
        System.out.println("\033[1;33m序列化之前的Group\033[0m:" + employee + "\n\n");

        //用来指向序列化的对象的二进制数组
        byte[] bytes;
        //用来指向反序列化后生成的对象
        EmployeeWithDiffId employee1;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(employee);
        bytes = bos.toByteArray();
        System.out.println("\033[1;33m[Java的Serializable接口序列化]得到的byte数组的长度:\033[0m" + bytes.length);

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        employee1 = (EmployeeWithDiffId) ois.readObject();
        System.out.println("\033[1;33m[Java的Serializable接口序列化]得到的Employee对象:\033[0m" + employee1);
    }
}
