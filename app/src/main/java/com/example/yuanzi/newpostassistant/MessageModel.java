package com.example.yuanzi.newpostassistant;

/**
 * Created by yuanzi on 2017/5/22.
 */

public class MessageModel {
    private String message_company;
    //private String message_huojia;
    public MessageModel(String company/*String huojia*/){
        this.message_company = company;
        //this.message_huojia = huojia;
    }
    public String getMessage_company(){
        return message_company;
    }
    /*public String getMessage_huojia(){
        return message_huojia;
    }*/

}
