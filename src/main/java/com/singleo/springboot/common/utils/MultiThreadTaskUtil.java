package com.singleo.springboot.common.utils;

import com.singleo.springboot.common.multithreadtask.MultiThreadTaskInterceptor;
import org.springframework.cglib.proxy.Enhancer;

import java.util.concurrent.Callable;

/**
 * 创建多线程代理类
 */
public class MultiThreadTaskUtil {

    private static Enhancer enhancer;

    public MultiThreadTaskUtil(){
        enhancer=new Enhancer();
        enhancer.setSuperclass(Callable.class);
    }

    public static Callable createMultiThreadTaskProxy(Callable callable){
        return (Callable) enhancer.create(callable.getClass(),new MultiThreadTaskInterceptor(callable));
    }
}
