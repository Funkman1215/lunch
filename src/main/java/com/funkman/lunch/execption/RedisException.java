package com.funkman.lunch.execption;

import com.funkman.lunch.resultEnum.ResultEnum;

public class RedisException extends RuntimeException {

    private Integer code;

    public RedisException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
