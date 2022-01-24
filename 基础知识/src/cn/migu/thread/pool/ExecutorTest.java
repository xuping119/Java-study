package cn.migu.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
实际不使用Executors，测试理解线程池使用方式
 */
public class ExecutorTest {
    public static void main(String[] args) {
        System.out.println("====================");
        test1();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====================");
        test2();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====================");

        test3();
    }

    //单个线程
    public static void test1() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"运行");
            });
        }

        threadPool.shutdown();
    }

    //固定数量线程
    public static void test2() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"运行");
            });
        }

        threadPool.shutdown();
    }

    //不固定数量线程
    public static void test3() {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"运行");
            });
        }

        threadPool.shutdown();
    }
}
