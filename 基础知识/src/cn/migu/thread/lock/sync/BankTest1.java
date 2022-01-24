package cn.migu.thread.lock.sync;

import java.util.concurrent.TimeUnit;

//synchronized 8种情况
public class BankTest1 {
    public static void main(String[] args) {
        //test1();

        test2();
    }

    //1.打印顺序是什么----- 存钱--> 取钱
    //原因：锁住了同一个对象，并且takeInMoney1先获取到锁
    public static void test1() {
        Bank1 bank1 = new Bank1();
        new Thread(()->{
            bank1.takeInMoney1();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            bank1.takeOffMoney();
        }).start();
    }

    //2.打印顺序是什么----- 存钱--> 取钱
    //原因：锁住了同一个对象，并且takeInMoney1先获取到锁
    public static void test2() {
        Bank1 bank1 = new Bank1();
        new Thread(()->{
            bank1.takeInMoney2();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            bank1.takeOffMoney();
        }).start();
    }
}

//资源类
class Bank1{

    public synchronized void takeInMoney1(){
        System.out.println("存钱！");
    }
    public synchronized void takeInMoney2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("存钱！");
    }

    public synchronized void takeOffMoney(){
        System.out.println("取钱！");
    }
}