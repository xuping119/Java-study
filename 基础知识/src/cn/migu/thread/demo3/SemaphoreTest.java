package cn.migu.thread.demo3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
信号量，线程安全，使用场景
1.共享变量互斥
2.线程访问限流
 */
public class SemaphoreTest {

    /*
        停车位业务：3个车位，6辆车
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+" 等待停车");
                    semaphore.acquire();//获取到车位
                    System.out.println(Thread.currentThread().getName()+" 停车");
                    //停车一段时间
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放车位
                    System.out.println(Thread.currentThread().getName()+" 离开了。");
                }

            },"第"+i+"辆车").start();
        }
    }
}
