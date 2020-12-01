package com.honchipay.honchi_android.chat.model;

import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.time.LocalDateTime;

public class MessageResponse {
    private Integer id;
    private String name;
    private String message;
    private MessageType messageType;
    private LocalDateTime time;
    private boolean isDeleted;
    private Integer userId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public MessageResponseByServer wrapping(int readCount) {
        MessageResponseByServer messageResponseByServer = new MessageResponseByServer();
        messageResponseByServer.setMessageId(id);
        messageResponseByServer.setUserId(userId);
        messageResponseByServer.setMessage(message);
        messageResponseByServer.setNickName(name);
        messageResponseByServer.setTime(time);
        messageResponseByServer.setReadCount(readCount);
        messageResponseByServer.setMessageType(messageType);
        messageResponseByServer.setDelete(isDeleted);
        messageResponseByServer.setMine(name.equals(SharedPreferencesManager.getInstance().getUserName()));

        return messageResponseByServer;
    }
}