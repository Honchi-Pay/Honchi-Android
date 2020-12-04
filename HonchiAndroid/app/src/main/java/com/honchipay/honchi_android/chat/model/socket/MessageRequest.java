package com.honchipay.honchi_android.chat.model.socket;

public class MessageRequest {
    private String chatId;
    private String message;

    public MessageRequest(String chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }

    public String getChatId() {
        return chatId;
    }

    public String getMessage() {
        return message;
    }
}