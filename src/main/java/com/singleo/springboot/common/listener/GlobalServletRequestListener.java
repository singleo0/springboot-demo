package com.singleo.springboot.common.listener;

import com.singleo.springboot.common.handler.EvtTraceHandler;
import com.singleo.springboot.common.handler.LogConfigHandler;
import com.singleo.springboot.common.swaparea.SwapAreaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.util.Arrays;

@Component
public class GlobalServletRequestListener implements ServletRequestListener {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    EvtTraceHandler evtTraceHandler;

    @Resource
    LogConfigHandler logConfigHandler;

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        if(SwapAreaUtils.getCurrentSwapArea()==null){
            SwapAreaUtils.buildNewSwapArea();
        }
        SwapAreaUtils.setEvtTraceId(evtTraceHandler.createEvtTraceId());
        logConfigHandler.addLogConfigKeys(Arrays.asList("evtTraceId"));
        logger.info("初始化servlet请求");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.info("销毁servlet请求");
        logConfigHandler.removeAllLogConfigKeys();
        SwapAreaUtils.releaseCurrentSwapArea();
    }
}
