package com.honchipay.honchi_android.chat.model.socket;

public class ImageRequest {
    private String chatId;
    private Integer messageId;

    public ImageRequest(String chatId, Integer messageId) {
        this.chatId = chatId;
        this.messageId = messageId;
    }
}