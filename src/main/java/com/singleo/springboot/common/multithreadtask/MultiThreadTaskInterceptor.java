package com.singleo.springboot.common.multithreadtask;

import com.singleo.springboot.common.handler.LogConfigHandler;
import com.singleo.springboot.common.utils.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 拦截多线程call方法
 * 利用代理,为多线程方法增加MDC日志支持，共享父线程MDC
 * 待优化,将父线程交换区传递给子线程
 */
public class MultiThreadTaskInterceptor implements MethodInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Callable task;
    private final Map<String,String> cm= MDC.getCopyOfContextMap();
    public MultiThreadTaskInterceptor(Callable task){
        this.task=task;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MDC.setContextMap(cm);
        logger.info("多线程任务拦截成功");
        Object result=methodProxy.invoke(task,objects);
        return result;
    }
}
