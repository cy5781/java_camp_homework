package java0.homework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 方法3--Callable 和 Future task
 */
public class Homework03_3  {

    private static  int count = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        work();

        System.out.println("异步计算结果为：" + count);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
       // Thread.sleep(1500);
        System.out.println("主线程运行结束:");

    }

    //创建子线程的函数
    private static void work() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MethodClassCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        count =futureTask.get();
    }


}


