package cn.migu.thread.demo2.safe;


import java.util.concurrent.TimeUnit;

//票的数量
//买票的人：小明，小红，黄牛
public class BuyTickets2 {
    public static void main(String[] args) {
        Tickets tickets = new Tickets();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                tickets.sale();
            }
        },"小明").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                tickets.sale();
            }
        },"小张").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                tickets.sale();
            }
        },"黄牛").start();

    }
}

//面向对象思想：线程执行体就是一个资源类
class Tickets {
    private int ticketnum = 20;

    public synchronized void buy() {
        while (true){
            if (ticketnum <= 0){
                System.out.println(Thread.currentThread().getName() + "，票已卖完！！");
                break;
            }
            System.out.println(Thread.currentThread().getName()+" 买到第" + ticketnum +" 张票.");
            ticketnum --;
            try {
                //等价于 Thread.sleep()
                //**** sleep 不会释放锁 **********
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sale() {
        if (ticketnum <= 0){
            System.out.println(Thread.currentThread().getName() + "，票已卖完！！");
            return;
        }
        System.out.println(Thread.currentThread().getName()+" 买到第" + ticketnum +" 张票.");
        ticketnum --;
    }
}


