package cn.migu.thread.pool;


import java.util.concurrent.*;

/**
 * 实际使用ThreadPoolExecutor创建线程池，不使用Executors
 *     拒绝策略：
 *      AbortPolicy，抛出异常 RejectedExecutionException
 *      CallerRunsPolicy,由调用方执行 main运行
 *      DiscardPolicy,丢弃，不抛异常
 *      DiscardOldestPolicy,尝试去获取线程，失败则丢弃
 */
public class ThreadPoolExecutorTest {
    /**
     最大线程数如何确定：
     1.CPU密集型，一般和机器的逻辑CPU数一致，Runtime.getRuntime().availableProcessors()
     2.IO密集型，一般设置为耗时任务数量的2倍，经验值是 2*CPU
     */
    public static void main(String[] args) {
        System.out.println("当前机器逻辑CPU数量："+Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 9; i++) {
            final int tmp = i+1;
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+", 任务"+tmp+" 运行");
            });
        }

        threadPool.shutdown();
    }
}
