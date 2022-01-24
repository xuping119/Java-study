package cn.migu.thread.lock.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
实现多个线程执行 读写操作

问题：1.无法通知/唤醒 指定线程
     需要使用lock锁+condition 实现
 */
public class MultiThreadTest {
    public static void main(String[] args) {
        ShareMsg shareMsg = new ShareMsg();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareMsg.push();
            }

        },"A-1").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareMsg.push();
            }
        },"A-2").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareMsg.pop();
            }
        },"C-1").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                shareMsg.pop();
            }
        },"C-2").start();
    }
}


class ShareMsg{
    private List list = new ArrayList();

    public synchronized void push(){
        /*
        若用if判断，当满足执行条件时线程不能及时响应，而是继续等待被唤醒，唤醒后将从this.wait()后往下执行，
        而不会重新判断是否满足执行条件，从而发生异常。
         */
        while (list.size() == 1){ //使用while判断，避免if判断的异常
            try {
                //判断等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //业务漏记
        list.add(UUID.randomUUID().toString().substring(0,8));
        System.out.println(Thread.currentThread().getName()+" push "+ list);

        /*
        假死出现的主要原因可能是notify()连续唤醒同类，
        解决方法是使用notifyAll()唤醒所有等待同一共享资源的全部线程。
         */
        //通知
        this.notifyAll();//避免假死

    }

    public synchronized void pop(){
        //判断等待
        while (list.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //业务逻辑
        String val = list.get(0) + "";
        list.remove(0);
        System.out.println(Thread.currentThread().getName()+" pop "+ val);

        //通知其他线程
        this.notifyAll();
    }
}