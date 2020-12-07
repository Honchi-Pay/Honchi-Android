package com.honchipay.honchi_android.chat.model.socket;

public class ImageRequest {
    private final String chatId;
    private final Integer messageId;

    public ImageRequest(String chatId, Integer messageId) {
        this.chatId = chatId;
        this.messageId = messageId;
    }
}