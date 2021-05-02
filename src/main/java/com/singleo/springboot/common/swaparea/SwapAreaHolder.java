package com.singleo.springboot.common.swaparea;

public class SwapAreaHolder {
    private ThreadLocal<SwapArea> holder=new ThreadLocal<>();

    public void setCurrentSwapArea(SwapArea swapArea){
        holder.set(swapArea);
    }

    public SwapArea getCurrentSwapArea(){
        return holder.get();
    }

    public SwapArea removeCurrentSwapArea(){
        SwapArea swapArea=holder.get();
        holder.remove();
        return swapArea;
    }
}
