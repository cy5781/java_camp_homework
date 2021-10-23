package java0.homework.week5.simpleAOP;

public class ProxyTest {

    public static void main(String[] args) {
        IStudentDao target= new StudentDao();
        System.out.println(target.getClass());
        IStudentDao proxy = (IStudentDao) new StudentProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.study();
    }
}
