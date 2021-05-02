package com.singleo.springboot.common.handler;

import com.singleo.springboot.common.swaparea.SwapAreaUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 日志MDC控制，从swaparea交换区获取值
 */
@Component
public class LogConfigHandler implements Handler{
    public void addLogConfigKeys(List<String> keyList){
        if(keyList!=null && keyList.size()>0){
            for(String key : keyList){
                MDC.put(key, (String) SwapAreaUtils.getCurrentSwapArea().getValue(key));
            }
        }
    }

    public void addLogConfigKeys(Map<String, String> addKeyMap){
        if(addKeyMap!=null && addKeyMap.size()>0){
            for(String key:addKeyMap.keySet()){
                MDC.put(key, addKeyMap.get(key));
            }
        }
    }

    public void removeLogConfigKeys(List<String> keyList){
        if(keyList!=null && keyList.size()>0){
//            Iterator iterator=keyList.iterator();
//            while (iterator.hasNext()){
//                String key=(String) iterator.next();
//                MDC.remove(key);
//                keyList.remove(key);
//                iterator=keyList.iterator();
//            }
            for(String key : keyList){
                MDC.remove(key);
            }
        }
    }

    public void removeAllLogConfigKeys(){
        MDC.clear();
    }
}
