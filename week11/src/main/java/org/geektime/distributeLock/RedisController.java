package org.geektime.distributeLock;

import org.geektime.distributeCounter.RedisCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("lock")
public class RedisController {

    @Autowired
    RedisDistributeLock redisLock;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisCount redisCount;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    int count = 0;

    @RequestMapping("/index")
    @ResponseBody
    public String index() throws InterruptedException {

        int clientcount =100;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        long start = System.currentTimeMillis();
        for (int i = 0;i<clientcount;i++){
            executorService.execute(() -> {

                //通过Snowflake算法获取唯一的ID字符串
                String id = String.valueOf(new SnowFlake(1,2).nextId());
                try {
                    redisLock.lock(id);
                    count++;
                }finally {
                    redisLock.unlock(id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        logger.info("执行线程数:{},总耗时:{},count数为:{}",clientcount,end-start,count);
        return "Hello";
    }

//    @RequestMapping("/test")
//    @ResponseBody
//    public String test() throws InterruptedException {
//        return "Hello World";
//    }


    @RequestMapping("/hello")
    public String hello(@RequestParam("id") String id){
        redisCount.incr(id);
        System.out.println("参数id为："+id);
        return id;
    }

    @RequestMapping("/get")
    public String get(String id){
        System.out.println("参数："+id+"访问次数："+ redisCount.get(id));
        return "参数："+id+"访问次数："+ redisCount.get(id);
    }

}
