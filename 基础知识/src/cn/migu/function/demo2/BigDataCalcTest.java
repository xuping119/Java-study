package cn.migu.function.demo2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 题目:对 1...10亿 求和，并统计耗时
 * 1.普通算法，累加
 * 2.forkjoin,拆分任务
 * 3.stream 并行计算
 */
public class BigDataCalcTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    //普通算法，累加 254ms
    public static void test1() {
        long start = System.currentTimeMillis();
        long sum = 0l;
        for (long i = 1; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();

        System.out.println("1...10亿 sum :"+sum+",耗时："+(end-start)+"ms");
    }

    //forkjoin,拆分任务 141ms
    public static void test2() {
        long start = System.currentTimeMillis();
        ForkJoinPool fp = new ForkJoinPool();
        ForkJoinTask<Long> submit = fp.submit(new CalcTask(1,10_0000_0000));
        long sum = 0;
        try {
            sum = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("1...10亿 sum :"+sum +",耗时："+(end-start)+"ms");
    }

    //stream 并行计算 122ms
    public static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1,10_0000_0000).parallel().reduce(0,Long::sum);

        long end = System.currentTimeMillis();

        System.out.println("1...10亿 sum :"+sum+",耗时："+(end-start)+"ms");
    }
}


/**
 * ForkJoinTask 有两个子类：都是递归处理
 * 1.RecursiveAction：事件驱动，没有返回值
 * 2.RecursiveTask，有返回值
 */
class CalcTask extends RecursiveTask<Long> {
    private long start;
    private long end;
    private final long BaseNum = 10000l;

    public CalcTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end-start) < BaseNum){
            long sum = 0l;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {
            //ForkJoin
            long mid = (start+end)/2;
            CalcTask c1 = new CalcTask(start,mid);
            CalcTask c2 = new CalcTask(mid+1,end);
            c1.fork();
            c2.fork();

            return c1.join()+c2.join();
        }
    }
}