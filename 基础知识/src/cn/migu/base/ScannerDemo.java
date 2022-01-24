package cn.migu.base;

import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        //testScanner();
        testCalc();
    }

    public static void testCalc(){
        //获取输入流
        Scanner sc = new Scanner(System.in);

        //等待键盘输入
        System.out.println("请输入：");
        double val = 0l;
        double sum = 0l;
        int num = 0;
        while (sc.hasNextDouble()){
            num++;
            val = sc.nextDouble();
            sum += val;
            System.out.println("输入的和是:" + sum+" , 平均值是："+sum/num);
        }
        System.out.println("程序结束！");
        //关闭输入流
        sc.close();
    }

    //测试 监听键盘输入
    public static void testScanner() {
        //获取输入流
        Scanner sc = new Scanner(System.in);

        //等待键盘输入
        System.out.println("请输入：");
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            System.out.println("输入的是:" + str);
        }

        //关闭输入流
        sc.close();
    }
}
