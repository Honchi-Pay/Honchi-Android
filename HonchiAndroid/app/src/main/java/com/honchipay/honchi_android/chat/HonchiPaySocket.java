package com.honchipay.honchi_android.chat;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class HonchiPaySocket {
    private static HonchiPaySocket single_instance = null;
    public static StompClient stompClient;

    private HonchiPaySocket() {}

    public static HonchiPaySocket getInstance() {
        if (single_instance == null) {
            single_instance = new HonchiPaySocket();
        }

        return single_instance;
    }

    public void connectSocket() {
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "");
        stompClient.connect();
    }
}
