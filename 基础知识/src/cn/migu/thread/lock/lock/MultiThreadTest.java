package cn.migu.thread.lock.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
实现多个线程执行 读写操作

问题：1.无法通知/唤醒 指定线程
     需要使用lock锁+condition 实现
 */
public class MultiThreadTest {
    public static void main(String[] args) {
        ShareState shareState = new ShareState();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareState.apply();
            }

        },"A-1").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareState.apply();
            }
        },"A-2").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareState.audit();
            }
        },"C-1").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareState.audit();
            }
        },"C-2").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareState.archived();
            }
        },"D-1").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareState.archived();
            }
        },"D-2").start();
    }
}


class ShareState{
    private int state = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public  void apply(){
        /*
        若用if判断，当满足执行条件时线程不能及时响应，而是继续等待被唤醒，唤醒后将从this.wait()后往下执行，
        而不会重新判断是否满足执行条件，从而发生异常。
         */
        lock.lock();
        try{
            while (state != 1){ //使用while判断，避免if判断的异常
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //业务漏记
            state = 2;
            System.out.println(Thread.currentThread().getName()+" 提出申请。");

        /*
        假死出现的主要原因可能是notify()连续唤醒同类，
        解决方法是使用notifyAll()唤醒所有等待同一共享资源的全部线程。
         */
            //通知
            c2.signalAll();//指定通知

        }finally {
            lock.unlock();
        }


    }

    public  void audit(){
        lock.lock();
        try{
            //判断等待
            while (state != 2){
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //业务逻辑
            state = 3;
            System.out.println(Thread.currentThread().getName()+" 审核通过。");

            //通知其他线程
            c3.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public  void archived(){
        lock.lock();
        try{
            //判断等待
            while (state != 3){
                try {
                    c3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //业务逻辑
            state = 1;
            System.out.println(Thread.currentThread().getName()+" 流程结束并归档。 ");

            //通知其他线程
            c1.signalAll();
        }finally {
            lock.unlock();
        }
    }
}