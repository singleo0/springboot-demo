package com.singleo.springboot.common.swaparea;

public class SwapAreaManager {
    private SwapAreaHolder holder;

    public SwapAreaManager(SwapAreaHolder swapAreaHolder){
        this.holder=swapAreaHolder;
    }

    public SwapArea builderNewSwapArea(){
        SwapArea swapArea=SwapArea.createNewSwapArea();
        holder.setCurrentSwapArea(swapArea);
        return swapArea;
    }

    public SwapArea getCurrentSwapArea(){
        return holder.getCurrentSwapArea();
    }

    public SwapArea releaseCurrentSwapArea(){
        return holder.removeCurrentSwapArea();
    }
}
