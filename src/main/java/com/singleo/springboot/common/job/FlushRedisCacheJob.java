package com.singleo.springboot.common.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 刷新redis
 */
public class FlushRedisCacheJob {
    Logger logger= LoggerFactory.getLogger(this.getClass());

    private Map<String, Object> flushRedisKeyMap;

    @Resource
    RedisTemplate redisTemplate;

    public void scheduled(){

        try {
            logger.info("刷新redis缓存工作开始了....");
            flashCache();
            logger.info("刷新redis缓存工作完成了....");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("刷新redis缓存工作发生了异常...."+e.getMessage());
        }

    }

    private void flashCache() {
        for(String key:flushRedisKeyMap.keySet()){
            String value=(String) redisTemplate.opsForValue().get(key);
            logger.info("redis刷新前,key="+key+",value="+value);
            redisTemplate.delete(key);
            redisTemplate.opsForValue().set(key,value);
            logger.info("redis刷新后,key="+key+",value="+value);
        }
    }

    public void setFlushRedisKeyList(Map flushRedisKeyMap) {
        this.flushRedisKeyMap=flushRedisKeyMap;
        logger.info("定时刷新的redis="+this.flushRedisKeyMap);
    }
}
