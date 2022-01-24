package cn.migu.thread.demo3;

import java.util.concurrent.CountDownLatch;

//减法计数器，线程安全的
public class CountDownTest {
    public static void main(String[] args) {
        Room room = new Room();

        new Thread(()->{
            room.close();
        },"王老师").start();

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                room.open();
            },i+"号").start();
        }
    }

}

class Room {
    private CountDownLatch countDownLatch = new CountDownLatch(10);

    public void open(){
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName()+" 离开了");
    }

    public void close(){
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("教室空了, "+Thread.currentThread().getName()+" 把门关了。");
    }
}