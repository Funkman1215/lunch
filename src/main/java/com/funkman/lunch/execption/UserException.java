package com.funkman.lunch.execption;

import com.funkman.lunch.resultEnum.ResultEnum;

public class UserException extends RuntimeException {
    private Integer code;

    public UserException(ResultEnum resultEnum) {
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
