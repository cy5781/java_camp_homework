package java0.homework.week5.simpleAOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StudentProxyFactory {

    private Object target;

    public StudentProxyFactory(Object target) {
        this.target = target;
    }

    //生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("AOP学习前");

                Object returnValue=method.invoke(target,args);

                System.out.println("AOP学习后");

                return null;
            }
        });
    }

}
