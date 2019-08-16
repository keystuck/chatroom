package com.example.chatroom.chat;

import com.alibaba.fastjson.annotation.JSONField;

public class Message {
    @JSONField(name = "username")
    private String username;
    @JSONField(name = "msg")
    private String message;
    @JSONField(name = "onlineCount")
    private int onlineCount;

    public Message(String username, String message){
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }
}
