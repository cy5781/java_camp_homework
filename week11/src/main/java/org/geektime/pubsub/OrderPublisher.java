package org.geektime.pubsub;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OrderPublisher {
    private Jedis publisherJedis;
    private String channel;

    public OrderPublisher(Jedis publishJedis,String channel){
        this.publisherJedis=publishJedis;
        this.channel=channel;
    }
    public void startPublish(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.println("请输入order message:");
                String line = reader.readLine();
                if(!"quit".equals(line)){
                    publisherJedis.publish(channel, line);
                }else{
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
