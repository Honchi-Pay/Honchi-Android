package com.honchipay.honchi_android.chat;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class HonciPaySocket extends WebSocketListener {
    private static HonciPaySocket single_instance = null;

    private static final int NORMAL_CLOSURE_STATUS = 1000;

    @Override
    public void onOpen(WebSocket webSocket, Response response) { }

    @Override
    public void onMessage(WebSocket webSocket, String text) { }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) { }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) { }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) { }

    public static HonciPaySocket getInstance() {
        if (single_instance == null) {
            single_instance = new HonciPaySocket();
        }

        return single_instance;
    }
}
