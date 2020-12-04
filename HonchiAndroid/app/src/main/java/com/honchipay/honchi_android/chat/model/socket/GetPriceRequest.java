package com.honchipay.honchi_android.chat.model.socket;

public class GetPriceRequest {
    private String chatId;
    private Integer price;

    public GetPriceRequest(String chatId, Integer price) {
        this.chatId = chatId;
        this.price = price;
    }

    public String getChatId() {
        return chatId;
    }

    public Integer getPrice() {
        return price;
    }
}
