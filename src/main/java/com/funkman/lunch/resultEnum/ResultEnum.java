package com.funkman.lunch.resultEnum;

public enum ResultEnum {
    EMPTY_ARGS(800, "参数为空"),
    PLURA_ARG(801,"有参数为负数"),
    ;

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
