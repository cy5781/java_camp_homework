package java0.homework.week5.beanAssemble.codeAssemble;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBean {

    //测试Java 代码装配Bean
    @Test
    public void test01()
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        MusicPlayer mp = (MusicPlayer) context.getBean("getMusicPlayer");
        mp.play();
    }
}
