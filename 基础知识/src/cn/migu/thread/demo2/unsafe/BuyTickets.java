package cn.migu.thread.demo2.unsafe;


//票的数量
//买票的人：小明，小红，黄牛
public class BuyTickets implements Runnable{

    private int ticketnum = 10;


    @Override
    public void run() {
        while (true){
            if (ticketnum <= 0){
                System.out.println(Thread.currentThread().getName() + "，票已卖完！！");
                break;
            }
            System.out.println(Thread.currentThread().getName()+" 买到第" + ticketnum +" 张票.");
            ticketnum --;
        }
    }

    public static void main(String[] args) {
        BuyTickets bt = new BuyTickets();
        Thread t1 = new Thread(bt,"小明");
        Thread t2 = new Thread(bt,"小红");
        Thread t3 = new Thread(bt,"黄牛");

        t1.start();
        t2.start();
        t3.start();
    }
}



