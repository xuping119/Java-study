package cn.migu.reflect.demo2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射创建对象
 */
public class RelectObject {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        //获取类对象
        Class clzz = Class.forName("cn.migu.reflect.demo2.Car");

        //通过反射创建car对象
        //默认通过无参构造器创建
        Car c1 = (Car) clzz.newInstance();
        System.out.println(c1);

        //使用有参构造器创建
        Constructor constructor = clzz.getDeclaredConstructor(String.class,int.class);
        Car c2 = (Car) constructor.newInstance("别克-昂科威",260000);
        System.out.println(c2);

        //直接调用方法
        c2.sell();

        //通过反射调用方法
        Method m1 = clzz.getDeclaredMethod("setPrice", int.class);
        m1.invoke(c2,190000);
        c2.sell();
    }
}
