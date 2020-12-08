package com.honchipay.honchi_android.chat.model.socket;

public class MessageRequest {
    private String chatId;
    private String message;

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatId() {
        return chatId;
    }

    public String getMessage() {
        return message;
    }
}