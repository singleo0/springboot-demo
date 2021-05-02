package com.singleo.springboot.app.multithreadtask;

import com.singleo.springboot.common.handler.LogConfigHandler;
import com.singleo.springboot.common.swaparea.SwapAreaUtils;
import com.singleo.springboot.common.utils.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class CompanyMultiThreadTask implements Callable {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public AtomicInteger count;
    public CompanyMultiThreadTask(){}
    public CompanyMultiThreadTask(AtomicInteger count){
        this.count=count;
    }

    @Override
    public Object call() throws Exception {
        count.decrementAndGet();
        logger.info("count="+count);
        return null;
    }
}
