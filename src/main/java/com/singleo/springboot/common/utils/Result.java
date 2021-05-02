package com.singleo.springboot.common.utils;

public class Result {
    private int stateCode;

    private Object content;

    private Result(){

    }

    private Result(int stateCode, Object content){
        this.stateCode = stateCode;
        this.content = content;
    }

    public static Result resultInfo(int stateCode, Object content){
        return new Result(stateCode, content);
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
