package com.singleo.springboot.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalMethodAspect {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    public void beforeMethod(JoinPoint joinPoint){
        logger.info("\r\n\t切入方法before成功," +
                "\r\n\tmethod="+joinPoint.getSignature()+"," +
                "\r\n\targs="+ JSON.toJSONString(joinPoint.getArgs(), SerializerFeature.WriteMapNullValue));
    }

    public void aroundMethod(ProceedingJoinPoint joinPoint){
        Object result=null;
        logger.info("\r\n\t切入方法around-before成功," +
                "\r\n\tmethod="+joinPoint.getSignature()+"," +
                "\r\n\tresult="+JSON.toJSONString(joinPoint.getArgs(), SerializerFeature.WriteMapNullValue));
        try {
            result=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("\r\n\t切入方法around-after成功," +
                "\r\n\tmethod="+joinPoint.getSignature()+"," +
                "\r\n\tresult="+JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
    }

    public void afterThrowMethod(JoinPoint joinPoint,Throwable throwable){
        logger.info("\r\n\t切入方法afterThrow成功," +
                "\r\n\tmethod="+joinPoint.getSignature()+"," +
                "\r\n\tthrowable="+JSON.toJSONString(throwable.getMessage(), SerializerFeature.WriteMapNullValue));
    }

    public void afterReturnMethod(JoinPoint joinPoint,Object result){
        logger.info("\r\n\t切入方法after成功," +
                "\r\n\tmethod="+joinPoint.getSignature()+"," +
                "\r\n\tresult="+JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
    }
}
