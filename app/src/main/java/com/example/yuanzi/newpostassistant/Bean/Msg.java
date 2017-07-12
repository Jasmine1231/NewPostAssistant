package com.example.yuanzi.newpostassistant.Bean;

/**
 * Created by yuanzi on 2017/7/12.
 */

public class Msg {

    int status;
    String msg;
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Msg(int status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }
    public Msg() {
        super();
        // TODO Auto-generated constructor stub
    }
}

