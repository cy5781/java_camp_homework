package java0.homework;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MethodClassCyclicBarrier implements Callable<Integer> {

    private CyclicBarrier barrier;

    public MethodClassCyclicBarrier(CyclicBarrier barrier){
        this.barrier = barrier;
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
        barrier.await();
        System.out.println("计算完成 ... ");
        return sum();

    }
}
