package cn.migu.thread.lock.sync;

import java.util.concurrent.TimeUnit;

//synchronized 8种情况
public class BankTest2 {
    public static void main(String[] args) {
        //test3();

        test4();
    }

    //3.增加了一个普通方法，打印顺序是什么----- 咨询 --> 存钱
    //原因：锁住了同一个对象，并且takeInMoney1先获取到锁
    public static void test3() {
        Bank2 bank = new Bank2();
        new Thread(()->{
            bank.takeInMoney3();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            bank.request();
        }).start();
    }

    //4.两个bank对象，打印顺序是什么----- 取钱--> 存钱
    //原因：锁住了不同的对象，没有锁的效果
    public static void test4() {
        Bank2 bank1 = new Bank2();
        Bank2 bank2 = new Bank2();


        new Thread(()->{
            bank1.takeInMoney4();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            bank2.takeOffMoney();
        }).start();
    }
}

//资源类
class Bank2{

    //锁住对象(对象是方法的调用者)，该对象一次只能执行一个方法
    public synchronized void takeInMoney3(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("存钱！");
    }
    public synchronized void takeInMoney4(){
        try {
            TimeUnit.SECONDS.sleep(2);//等待2秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("存钱！");
    }

    public synchronized void takeOffMoney(){
        System.out.println("取钱！");
    }

    //普通方法，不被锁控制
    public void request(){
        System.out.println("咨询");
    }
}