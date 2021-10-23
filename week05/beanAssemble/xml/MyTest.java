package java0.homework.week5.beanAssemble.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testMethodAutowire() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Master user = (Master) context.getBean("master");
        user.getCat().eat();
        user.getDog().eat();
    }
}
