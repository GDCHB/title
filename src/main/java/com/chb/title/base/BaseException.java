package com.chb.title.base;

/**
 * Description :
 * Author : LuYun
 * Version : 1.0
 * Create Date Time : 2017/7/24 16:40.
 *
 * @see
 */
public class BaseException extends Exception {
    private String code;
    private String msg;

    public BaseException() {
    }

    public BaseException(String code) {
        this.code = code;
    }

    public BaseException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
