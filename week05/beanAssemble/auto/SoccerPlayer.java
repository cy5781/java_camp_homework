package java0.homework.week5.beanAssemble.auto;

import org.springframework.stereotype.Component;

@Component
public class SoccerPlayer implements SportsMan {

    private String name = "Bob";

    @Override
    public void action() {
        System.out.println(name+"在踢足球");
    }
}
