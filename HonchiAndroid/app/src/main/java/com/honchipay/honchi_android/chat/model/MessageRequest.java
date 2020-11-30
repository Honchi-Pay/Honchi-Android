package com.honchipay.honchi_android.chat.model;

public class MessageRequest {
    private final String roomId;
    private final String Message;

    public MessageRequest(String roomId, String Message) {
        this.roomId = roomId;
        this.Message = Message;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getMessage() {
        return Message;
    }
}