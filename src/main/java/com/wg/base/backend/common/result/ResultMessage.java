package com.wg.base.backend.common.result;

public class ResultMessage {

    private int code;
    private String msg;

    //通用的异常
    public static ResultMessage SUCCESS=new ResultMessage(0,"success");

    public static ResultMessage SERVER_ERROR=new ResultMessage(500,"服务端异常");
    public static ResultMessage MOBILE_EMPTY=new ResultMessage(50002,"手机号不能为空");
    public static ResultMessage MOBILE_ERROR=new ResultMessage(50003,"手机号格式错误");
    public static ResultMessage NO_USER = new ResultMessage(50004,"用户不存在");
    public static ResultMessage PASSWORD_ERROR = new ResultMessage(50005,"密码错误");
    public static ResultMessage TOKEN_EMPTY_ERROR=new ResultMessage(50006,"token为空");
    public static ResultMessage TOKEN_INVALID_ERROR=new ResultMessage(50007,"token错误或已失效");

    public ResultMessage fillArgs(Object...args){
        int code=this.code;
        String message=String.format(this.msg,args);
        return new ResultMessage(code,message);
    }

    public ResultMessage() {
    }

    private ResultMessage(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

}
