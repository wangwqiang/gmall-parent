package com.atguigu.gmall.item;

import java.util.concurrent.*;

/**
 * @author wangwqiang
 * date 2022/8/28
 * @version 1.0
 */
public class AsyncTest {
    static ExecutorService executor = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("aaa");
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 2;
        });

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            System.out.println("bbb");
        }, executor);

        //以上全部完成，进行下一步
        CompletableFuture.allOf(future1, future2, future3).join();
        System.out.println("执行结束");
        Thread.sleep(1000000L);
    }

    /**
     * thenXXX编排任务
     * <p>
     * CompletableFuture<Void>
     * CompletableFuture<Integer>
     * <p>
     * 1、thenRun()\thenRunAsync() future.thenXXXX() ： 接下来干什么 CompletableFuture<Void>
     * thenRun(runnable)：              不用异步跑任务，而是用主线程
     * thenRunAsync(runnable):          用异步跑任务，使用默认ForkJoin线程池
     * thenRunAsync(runnable,executor)
     * 接下来干活用不到上一步的结果，自己运行完也不返回任何结果 CompletableFuture<Void>
     * 2、thenAccept()\thenAcceptAsync()： CompletableFuture<Void>
     * thenAccept(consumer):       拿到上一步结果，不用异步跑任务，而是用主线程
     * thenAcceptAsync(consumer):  拿到上一步结果，用异步跑任务，使用默认ForkJoin线程池
     * thenAcceptAsync(consumer,executor)  拿到上一步结果，用异步跑任务，使用指定线程池
     * 3、thenApply()\thenApplyAsync()：  拿到上一步结果，还能自己返回新结果
     * thenApply(function)：        拿到上一步结果，不用异步跑任务，而是用主线程，并返回自己的计算结果
     * thenApplyAsync(function)：   拿到上一步结果，用异步跑任务，用默认线程池，并返回自己的计算结果
     * thenApplyAsync(function,executor)： 拿到上一步结果，用异步跑任务，用指定线程池，并返回自己的计算结果
     * <p>
     * thenRun： 不接收上一次结果，无返回值
     * thenAccept：接收上一次结果，无返回值
     * thenApply： 接收上一次结果，有返回值
     *
     * @param args
     */
    public static void then(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            return 2;
        }).thenApplyAsync((t) -> {
            return t + 3;
        }, executor).thenApply(t -> {
            return t * 6;
        }).thenAcceptAsync(t -> {
            System.out.println("将" + t + "保存到数据库中");
        }).whenComplete((t, u) -> {
            System.out.println("执行结束");
        });
    }


    public static void startAsync(String[] args) throws Exception {

        System.out.println(Thread.currentThread().getName() + "\t" + "主线程开始");
        //启动异步任务
        //1. runAsync() 没有返回值
        // executor 使用指定线程池  默认是ForkJoinPool
        /*CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "没有返回值");
        },executor);*/

        //2. supplyAsync()  有返回值
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "有返回值");
            return 2;
        }, executor);

        Integer integer = future.get();
        Integer integer1 = future.get(3, TimeUnit.SECONDS);
        System.out.println("异步结果 = " + integer1);

        Thread.sleep(10000000L);


    }
}
