package java0.homework.week5.beanAssemble.codeAssemble;

import org.springframework.stereotype.Component;

@Component
public class Music {
    private String title = "月光奏鸣曲";
    private String artist="贝多芬";
    public void play(){
        System.out.println("播放 "+artist+" 的 "+title);
    }
}
