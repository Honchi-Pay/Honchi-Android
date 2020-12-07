package com.honchipay.honchi_android.chat.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessageResponseByTime {
    private Integer id;
    private Integer userId;
    private String nickName;
    private String message;
    private MessageType messageType;
    private Integer readCount;
    private LocalDateTime time;
    private boolean mine;
    private boolean delete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String name) {
        this.nickName = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public MessageResponse converterOriginal() {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setId(id);
        messageResponse.setUserId(userId);
        messageResponse.setMessageType(messageType);
        messageResponse.setMessage(message);
        messageResponse.setDelete(delete);
        messageResponse.setMine(mine);
        messageResponse.setReadCount(readCount);
        messageResponse.setNickName(nickName);

        ArrayList<Integer> timeArrayList = new ArrayList<>();
        timeArrayList.add(time.getYear());
        timeArrayList.add(time.getMonth().getValue());
        timeArrayList.add(time.getDayOfMonth());
        timeArrayList.add(time.getHour());
        timeArrayList.add(time.getMinute());

        messageResponse.setTime(timeArrayList);

        return messageResponse;
    }
}