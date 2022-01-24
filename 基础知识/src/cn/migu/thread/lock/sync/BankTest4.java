package cn.migu.thread.lock.sync;

import java.util.concurrent.TimeUnit;

//synchronized 8种情况
public class BankTest4 {
    public static void main(String[] args) {
//        test7();

        test8();
    }

    //7.1个static 同步方法，1个普通同步方法，打印顺序是什么----- 取钱 --> 存钱
    //原因：锁住了不同对象
    public static void test7() {
        Bank4 bank = new Bank4();
        new Thread(()->{
            bank.takeInMoney7();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            bank.takeOffMoney7();
        }).start();
    }

    //8.两个bank对象，1个static 同步方法，1个普通同步方法，，打印顺序是什么----- 取钱 --> 存钱
    //原因：锁住了不同对象
    public static void test8() {
        Bank4 bank1 = new Bank4();
        Bank4 bank2 = new Bank4();


        new Thread(()->{
            bank1.takeInMoney7(); //锁住的对象是 CLASS对象
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            bank2.takeOffMoney7();//锁住的对象是 bank2
        }).start();
    }
}

//资源类
class Bank4{

    //锁住对象，该对象一次只能执行一个方法
    //对象是类对象 CLASS
    public static synchronized void takeInMoney7(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("存钱！");
    }

    public  synchronized void takeOffMoney7(){
        System.out.println("取钱！");
    }

}