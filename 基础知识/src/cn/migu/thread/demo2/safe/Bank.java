package cn.migu.thread.demo2.safe;

import java.util.concurrent.locks.ReentrantLock;

//两个人去银行取钱
public class Bank {

    public static void main(String[] args) {
        Account account = new Account("乐享账户",100);
        ReentrantLock lock = new ReentrantLock();

        Drawing d1 = new Drawing(account,50,"小张",lock);
        Drawing d2 = new Drawing(account,100,"小红",lock);
        d1.start();
        d2.start();
    }
}

//账户
class Account{
    public String name;
    public int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }
}

//取钱
class Drawing extends Thread{
    ReentrantLock lock;
    private Account account;
    private int drawingmoney;
    private int nowmoney;

    public Drawing(Account account, int drawingmoney, String name,ReentrantLock lock) {
        super(name);
        this.account = account;
        this.drawingmoney = drawingmoney;
        this.lock = lock;
    }

    //取钱
    @Override
    public void run() {
        lock.lock();
        try{
            //判断卡内余额
            if ((account.money - drawingmoney ) <0 ){
                System.out.println(Thread.currentThread().getName()+"，卡内余额不足，不能取钱了！");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡内余额
            account.money -= drawingmoney;

            //手里的钱
            nowmoney += drawingmoney;

            System.out.println(account.name+" 余额为："+account.money);
            System.out.println(this.getName()+" 手里的钱为："+nowmoney);
        }finally {
            lock.unlock();
        }


    }
}