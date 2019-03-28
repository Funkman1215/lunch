package com.funkman.lunch.entity;

public class Result<T> {

    public static Result success(Success success,Object object){
        return new Result(success.getCode(),success.getMsg(),object);
    }

    public static Result error(Error error,Object object) {
        return new Result(error.getCode(), error.getMsg(), object);

    }

    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {

        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
