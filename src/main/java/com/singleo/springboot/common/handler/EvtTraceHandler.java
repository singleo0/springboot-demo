package com.singleo.springboot.common.handler;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成事件跟踪号
 */
@Component
public class EvtTraceHandler implements Handler{
    private String evtTraceId;

    public String createEvtTraceId(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        evtTraceId=Thread.currentThread().getId()+"_"+sdf.format(new Date());
        return evtTraceId;
    }
}
