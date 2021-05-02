package com.singleo.springboot.app.service;

import com.singleo.springboot.app.multithreadtask.CompanyMultiThreadTask;
import com.singleo.springboot.common.utils.MultiThreadTaskUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CompanyService {
    public void multiThreadTrace(String s) throws Exception {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        AtomicInteger atomicInteger=new AtomicInteger(20);
        for(int i=0;i<20;i++){
            CompanyMultiThreadTask task=new CompanyMultiThreadTask(atomicInteger);
            executorService.submit(MultiThreadTaskUtil.createMultiThreadTaskProxy(task));
        }
        while (atomicInteger.get()>0){
            ;
        }
        executorService.shutdown();
        throw new Exception("test ex");
    }

}
