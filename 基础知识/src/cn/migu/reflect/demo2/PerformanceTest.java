package cn.migu.reflect.demo2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PerformanceTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        test1();
        test2();
        test3();
    }

    //正常调用
    public static void test1() {
        Car c1 = new Car("大众",12000);
        long start = System.currentTimeMillis();

        //调用10亿次
        for (int i = 0; i < 1000000000; i++) {
            c1.sell();
        }

        long end = System.currentTimeMillis();
        System.out.println("正常调用10亿次耗时："+(end-start)+" ms");
    }

    //反射调用
    public static void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Car c2 = new Car("大众",12000);
        Class clzz = c2.getClass();
        Method method = clzz.getDeclaredMethod("sell");
        long start = System.currentTimeMillis();

        //调用10亿次
        for (int i = 0; i < 1000000000; i++) {
            method.invoke(c2);
        }

        long end = System.currentTimeMillis();
        System.out.println("反射调用10亿次耗时："+(end-start)+" ms");
    }

    //反射调用,关闭权限检查
    public static void test3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Car c3 = new Car("大众",12000);
        Class clzz = c3.getClass();
        Method method = clzz.getDeclaredMethod("sell");
        method.setAccessible(true);
        long start = System.currentTimeMillis();

        //调用10亿次
        for (int i = 0; i < 1000000000; i++) {
            method.invoke(c3);
        }

        long end = System.currentTimeMillis();
        System.out.println("反射(关闭检查)调用10亿次耗时："+(end-start)+" ms");

    }
}
