package com.singleo.springboot.common.swaparea;

import java.util.HashMap;
import java.util.Map;

public class SwapArea {
    private Map<String, Object> swapArea;

    private SwapArea(){
        swapArea=new HashMap<>();
    }

    public static SwapArea createNewSwapArea(){
        return new SwapArea();
    }

    public Object getValue(String key){
        return swapArea.get(key);
    }

    public void setValue(String key, Object value){
        swapArea.put(key,value);
    }

    public Map<String,Object> getSwapArea(){
        return swapArea;
    }
}
