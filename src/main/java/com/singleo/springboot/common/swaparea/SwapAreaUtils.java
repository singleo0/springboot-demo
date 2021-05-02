package com.singleo.springboot.common.swaparea;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwapAreaUtils {
    private static SwapAreaManager manager;
    private static Logger logger= LoggerFactory.getLogger(SwapAreaUtils.class);

    public static SwapArea buildNewSwapArea(){
        if(manager!=null){
            return manager.builderNewSwapArea();
        }
        return null;
    }

    public static SwapArea getCurrentSwapArea(){
        if(manager!=null){
            return manager.getCurrentSwapArea();
        }
        return null;
    }

    public static SwapArea releaseCurrentSwapArea(){
        if(manager!=null && getCurrentSwapArea()!=null){
            return manager.releaseCurrentSwapArea();
        }
        return null;
    }

    public static String getEvtTraceId(){
        if(manager!=null){
            return (String) manager.getCurrentSwapArea().getValue("evtTraceId");
        }
        return "";
    }

    public static void setEvtTraceId(String value){
        if(manager!=null){
            manager.getCurrentSwapArea().setValue("evtTraceId",value);
        }
    }

    public void setManager(SwapAreaManager manager) {
        SwapAreaUtils.manager=manager;
    }
}
