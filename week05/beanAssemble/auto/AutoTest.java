package java0.homework.week5.beanAssemble.auto;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes=SportsConfig.class)
public class AutoTest {

    @Autowired
    SoccerPlayer soccerPlayer;

    @Test
    public void testSports(){
        soccerPlayer.action();
    }
}
