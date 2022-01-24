package cn.migu.thread.async;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程异步调用 CompletedFuture
 * 1.无返回值
 * 2.有返回值
 */
public class AsyncTest {
    public static void main(String[] args) {
        //test1();
        test2();
//        AtomicInteger

    }

    //无返回值，异步调用
    public static void test1() {
        CompletableFuture<Void> task = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": runAsync->Void");
        });
        System.out.println(Thread.currentThread().getName()+": test1->Void");
        try {
            task.get(); //获取阻塞执行结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    //有返回值，异步调用
    public static void test2() {
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": supplyAsync->Integer");
            int a  = 10/0;
            return 100;
        });
        System.out.println(Thread.currentThread().getName()+": test2->Void");

        try {
            System.out.println(task.whenComplete((t, u) -> {
                //t:正常返回的结果
                //u:返回的异常描述
                System.out.println("t:" + t + ",u:" + u);
            }).exceptionally((e) -> {
                System.out.println(e.getMessage());
                return 102;
            }).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
