package com.funkman.lunch.entity;

public class Success {
    private Integer code;
    private String msg;

    public Success(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
