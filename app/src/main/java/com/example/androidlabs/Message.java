package com.example.androidlabs;

public class Message {


    public String message;
    public boolean isSend;
    public long id;



    public Message(String message, boolean isSend) {
        this.message = message;
        this.isSend = isSend;
//        this.isReceived = isReceived;
    }

    public Message() {
    }


    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public boolean isSend() { return isSend; }

    public void setSend(boolean send) { isSend = send; }

    public long getId () {
        return id;
    }

    public void setId (long id) { this.id = id; }

//    public boolean getisReceived() { return isReceived; }
//
//    public void setIsReceived(boolean isReceived) { this.isReceived = isReceived; }
}
