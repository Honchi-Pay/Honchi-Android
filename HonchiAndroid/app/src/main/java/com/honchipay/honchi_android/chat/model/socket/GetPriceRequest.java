package com.honchipay.honchi_android.chat.model.socket;

public class GetPriceRequest {
    private final String chatId;
    private final Integer price;

    public GetPriceRequest(String chatId, Integer price) {
        this.chatId = chatId;
        this.price = price;
    }
}
