package com.itwanli.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultModel implements Serializable {

    private int code;
    private String msg;
    private Object data ;

    public ResultModel(){

    }

    public ResultModel(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
