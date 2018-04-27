package com.genlot.lottery.net;

/**
 * Date 2018/3/16
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 统一返回封装
 */

public class BaseResponse<T> {
    private int resCode;         //返回状态代码

    private String resMsg;         //返回信息

    private T content;                 //返回数据

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
