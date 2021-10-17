package java0.homework;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class MethodClassCallable implements Callable<Integer> {

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
        return sum();
    }
}
