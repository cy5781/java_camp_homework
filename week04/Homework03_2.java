package java0.homework;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 方法1--用死循环的方法等待子线程的值
 */
public class Homework03_2 {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        work();

        // 等待拿到count
        while (count == 0) {
            Thread.sleep(1000);
        }
        System.out.println("异步计算结果为：" + count);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // Thread.sleep(1500);
        System.out.println("主线程运行结束:");

    }


    //创建子线程的函数
    private static void work() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                count = MethodClass.sum();
            }
        });
        thread.start();
    }


}


