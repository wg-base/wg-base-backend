package com.wg.base.backend.common.result;

public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private Result(T data){
        this.code=0;
        this.msg="success";
        this.data=data;
    }

    private Result(int code,String msg) {
        this.code=code;
        this.msg=msg;
    }

    public static <T>  Result<T>  success(T data){
        return new Result<T>(data);
    }

    public static <T>  Result<T>  fail(int code,String msg){
        return  new Result<T>(code,msg);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}
