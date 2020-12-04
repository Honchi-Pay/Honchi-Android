package com.honchipay.honchi_android.chat.model.socket;

public class ChangeTitleRequest {
    private String chatId;
    private String title;

    public ChangeTitleRequest(String chatId, String title) {
        this.chatId = chatId;
        this.title = title;
    }

    public String getChatId() {
        return chatId;
    }

    public String getTitle() {
        return title;
    }
}