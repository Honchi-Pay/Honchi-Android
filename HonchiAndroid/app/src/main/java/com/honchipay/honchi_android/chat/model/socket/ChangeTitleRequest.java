package com.honchipay.honchi_android.chat.model.socket;

public class ChangeTitleRequest {
    private final String chatId;
    private final String title;

    public ChangeTitleRequest(String chatId, String title) {
        this.chatId = chatId;
        this.title = title;
    }
}