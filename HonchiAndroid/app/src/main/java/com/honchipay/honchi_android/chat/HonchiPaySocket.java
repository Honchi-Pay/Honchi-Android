package com.honchipay.honchi_android.chat;

public class HonchiPaySocket {
    private static HonchiPaySocket single_instance = null;

    private HonchiPaySocket() {}
    public static HonchiPaySocket getInstance() {
        if (single_instance == null) {
            single_instance = new HonchiPaySocket();
        }

        return single_instance;
    }

    
}
