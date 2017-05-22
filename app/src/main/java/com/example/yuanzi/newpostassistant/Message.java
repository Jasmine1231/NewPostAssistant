package com.example.yuanzi.newpostassistant;

/**
 * Created by yuanzi on 2017/5/21.
 */

public class Message {
    private String message_time;
    private String d_company;
    private String d_phone_number;
    private String d_isget;

    public Message(String message_time,String company ,
                   String phone_number,String isget){
        this.message_time = message_time;
        this.d_company = company;
        this.d_phone_number = phone_number;
        this.d_isget = isget;
    }

    public String getMessage_time(){return message_time;}

    public String getD_company(){
        return d_company;
    }

    public String getD_phone_number(){
        return d_phone_number;
    }

    public String getD_isget(){
        return d_isget;
    }
}
