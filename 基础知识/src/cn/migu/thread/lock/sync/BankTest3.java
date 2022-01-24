package cn.migu.thread.lock.sync;

import java.util.concurrent.TimeUnit;

//synchronized 8种情况
public class BankTest3 {
    public static void main(String[] args) {
//        test5();

        test6();
    }

    //5.增加了两个static 同步方法，打印顺序是什么----- 存钱 --> 取钱
    //原因：锁住了同一个对象-- 类对象 CLASS ，并且takeInMoney5先获取到锁
    public static void test5() {
        Bank3 bank = new Bank3();
        new Thread(()->{
            bank.takeInMoney5();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            bank.takeOffMoney5();
        }).start();
    }

    //6.两个bank对象，增加了两个static 同步方法，打印顺序是什么----- 存钱 --> 取钱
    //原因：锁住了同一个对象-- 类对象 CLASS
    public static void test6() {
        Bank3 bank1 = new Bank3();
        Bank3 bank2 = new Bank3();


        new Thread(()->{
            bank1.takeInMoney5();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            bank2.takeOffMoney5();
        }).start();
    }
}

//资源类
class Bank3{

    //锁住对象，该对象一次只能执行一个方法
    //对象是类对象 CLASS
    public static synchronized void takeInMoney5(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("存钱！");
    }

    public static synchronized void takeOffMoney5(){
        System.out.println("取钱！");
    }

}