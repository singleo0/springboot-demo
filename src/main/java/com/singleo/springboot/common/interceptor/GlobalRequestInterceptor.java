package com.singleo.springboot.common.interceptor;

import com.singleo.springboot.common.handler.EvtTraceHandler;
import com.singleo.springboot.common.swaparea.SwapAreaUtils;
import com.singleo.springboot.common.utils.CusAccessObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalRequestInterceptor extends HandlerInterceptorAdapter {
    Logger globalInterceptLogger= LoggerFactory.getLogger("logger_GlobalInterceptLogger");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        String requestUri=request.getRequestURI();
        String reqContentType=request.getContentType();
        globalInterceptLogger.info("请求拦截成功,ip="+ CusAccessObjectUtil.getIpAddress(request)
                +",tms="+tms
                +",requestUri="+requestUri
                +",requestContentType="+reqContentType);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String tms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        String requestUri=request.getRequestURI();
        String reqContentType=request.getContentType();
        globalInterceptLogger.info("响应拦截成功,ip="+CusAccessObjectUtil.getIpAddress(request)
                +",tms="+tms
                +",requestUri="+requestUri
                +",requestContentType="+reqContentType);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String tms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        String requestUri=request.getRequestURI();
        String reqContentType=request.getContentType();
        globalInterceptLogger.info("请求结束,ip="+CusAccessObjectUtil.getIpAddress(request)
                +",tms="+tms
                +",requestUri="+requestUri
                +",requestContentType="+reqContentType);
    }

}