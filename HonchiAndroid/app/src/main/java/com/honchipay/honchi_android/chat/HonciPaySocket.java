package com.honchipay.honchi_android.chat;


public class HonciPaySocket {
    private static HonciPaySocket single_instance = null;

    private HonciPaySocket() {

    }

    public static HonciPaySocket getInstance() {
        if (single_instance == null) {
            single_instance = new HonciPaySocket();
        }

        return single_instance;
    }
}
