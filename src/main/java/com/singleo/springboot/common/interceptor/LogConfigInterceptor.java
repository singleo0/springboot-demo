package com.singleo.springboot.common.interceptor;

import com.singleo.springboot.common.handler.EvtTraceHandler;
import com.singleo.springboot.common.handler.LogConfigHandler;
import com.singleo.springboot.common.swaparea.SwapAreaUtils;
import com.singleo.springboot.common.utils.CusAccessObjectUtil;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LogConfigInterceptor extends HandlerInterceptorAdapter {

    private LogConfigHandler logConfigHandler;
    private List<String> initKeyList;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logConfigHandler.addLogConfigKeys(initKeyList);
        return true;
    }

    public void setInitKeyList(List<String> initKeyList) {
        this.initKeyList=initKeyList;
    }

    public void setLogConfigHandler(LogConfigHandler logConfigHandler) {
        this.logConfigHandler=logConfigHandler;
    }
}
