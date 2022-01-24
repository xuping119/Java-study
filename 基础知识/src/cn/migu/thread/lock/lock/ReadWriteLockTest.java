package cn.migu.thread.lock.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
ReadWriteLock:读写锁
独占锁：写锁
共享锁：读锁
*************
读读：共享
读写：独占
写写：独占
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        //testMyCachNoLock();
        testMyCachLock();
    }

    public static void testMyCachNoLock() {
        MyCachNoLock myCach = new MyCachNoLock();

        //多线程写
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCach.put("线程"+ temp,""+ temp);
            },"线程"+i).start();
        }

        //多线程写
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCach.get("线程"+ temp);
            },"线程"+i).start();
        }
    }

    public static void testMyCachLock() {
        MyCachLock myCach = new MyCachLock();

        //多线程写
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCach.put("线程"+ temp,""+ temp);
            },"线程"+i).start();
        }

        //多线程写
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCach.get("线程"+ temp);
            },"线程"+i).start();
        }
    }
}


//无锁的缓存
class MyCachNoLock{
    private volatile Map<String,Object> map = new HashMap<String,Object>();
    public void put(String key,Object val){
        System.out.println(Thread.currentThread().getName()+"写入:"+key);
        map.put(key,val);
        System.out.println(Thread.currentThread().getName()+"写入完成");
    }

    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取:"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取完成");
    }
}


//读写锁的缓存
class MyCachLock{
    private volatile Map<String,Object> map = new HashMap<String,Object>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object val){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"写入:"+key);
            map.put(key,val);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取:"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完成");
        }finally {
            readWriteLock.readLock().unlock();
        }

    }
}
