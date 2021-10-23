package java0.homework.week5.beanAssemble.codeAssemble;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public Music getMusic(){
        return new Music();
    }

    @Bean
    public MusicPlayer getMusicPlayer(Music music){
        return new MusicPlayer(music);
    }
}
