package org.example;

import org.example.pojo.Employee;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create at 16:52 2021/7/26
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Employee employee = Enums.EmployeeEnum.ZHANGSAN.getEmployee();
        System.out.println("\033[1;33m序列化之前的Group\033[0m:" + employee + "\n\n");

        //用来指向序列化的对象的二进制数组
        byte[] bytes;
        //用来指向反序列化后生成的对象
        Employee employee1;

        //a.Java的Serializable接口序列化
        /**
         * 1.将对象输出流转为其他形式的输出流，这里我转为了二进制数组输出流
         * 也可以转换为其他形式的输出流,比如
         * FileOutputStream fos = new FileOutputStream("E:\\xx.xx");
         * ObjectOutputStream oos = new ObjectOutputStream(fos);
         * 输出生成了xx.xx二进制文件
         */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        /**
         * 2.通过 ObjectOutputStream::writeObject 方法将对象序列化至输出流
         */
        oos.writeObject(employee);
        /**
         * 3.获取输出流中二进制数组的拷贝
         */
        bytes = bos.toByteArray();
        System.out.println("\033[1;33m[Java的Serializable接口序列化]得到的byte数组的长度:\033[0m" + bytes.length);
        /**
         * 4.将数据重新输入对象输入流
         */
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        /**
         * 5.通过 ObjectInputStream::
         */
        employee1 = (Employee) ois.readObject();
        System.out.println("\033[1;33m[Java的Serializable接口序列化]得到的Employee对象:\033[0m" + employee1);
    }
}
