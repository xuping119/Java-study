package cn.migu.thread.demo3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//加法计数器，线程安全
public class CyclicBarrierTest {

    //系统架构师考试，三门通过，才算通过
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
            System.out.println("总算三门都过了，不容易啊！！");
        });

        for (int i = 1; i <= 3; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 通过了。");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"第"+i+"门考试").start();
        }
    }
}
