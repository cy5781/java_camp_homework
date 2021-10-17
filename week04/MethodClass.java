package java0.homework;

public class MethodClass {
    public static int sum() {
        int a = fibo(36);
        return a;
    }

    public static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
