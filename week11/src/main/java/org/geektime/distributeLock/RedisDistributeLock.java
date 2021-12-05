package org.geektime.distributeLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

@Service
public class RedisDistributeLock {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String lock_key = "redis_lock";
    protected long lockReleaseTime = 30000;
    private long timeout = 999999;

    SetParams params = SetParams.setParams().nx().px(lockReleaseTime);

    @Autowired
    JedisPool jedisPool;

    public boolean lock(String id) {
        Jedis jedis = jedisPool.getResource();
        Long startTime = System.currentTimeMillis();
        try {
            for (; ; ) {
                String lock_status = jedis.set(lock_key, id, params);
                //get lock successful
                if ("OK".equals(lock_status)) {
                    return true;
                }

                //or wait
                long waitTime = System.currentTimeMillis() - startTime;
                if (waitTime > timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close();
        }
    }

    public boolean unlock(String id) {
        Jedis jedis = jedisPool.getResource();
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                "   return redis.call('del',KEYS[1]) " +
                "else" +
                "   return 0 " +
                "end";
        try {
            Object result = jedis.eval(script, Collections.singletonList(lock_key),Collections.singletonList(id));
            if("1".equals(result.toString())){
                return true;
            }
            return false;
        } finally {
            jedis.close();
        }
    }


}
