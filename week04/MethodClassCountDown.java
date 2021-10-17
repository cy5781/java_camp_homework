package java0.homework;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class MethodClassCountDown implements Callable<Integer> {

    private CountDownLatch latch;

    public MethodClassCountDown(CountDownLatch latch){
        this.latch = latch;
    }

    public static int sum() {
        int a = fibo(36);
        return a;
    }

    public static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("等待计算中 ... ");
        //latch.await();
        System.out.println("计算完成 ... ");
        latch.countDown();
        return sum();

    }
}
