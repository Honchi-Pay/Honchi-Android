package com.honchipay.honchi_android.chat;

import android.util.Log;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class HonchiPaySocket {
    private static final String TAG = HonchiPaySocket.class.getSimpleName();
    private static final String SERVER_URL = "http://:8000";
    private boolean isConnected = false;
    private static HonchiPaySocket single_instance = null;
    public Socket socket;

    public static HonchiPaySocket getInstance() {
        if (single_instance == null) {
            single_instance = new HonchiPaySocket();
        }

        return single_instance;
    }

    public void connect() {
        try {
            socket = IO.socket(SERVER_URL);
            socket.connect();

            isConnected = true;
            Log.e(TAG, "success connected");
        } catch (URISyntaxException e) {
            e.printStackTrace();

            isConnected = false;
        }
    }

    public void joinIntoRoom(String roomId) {
        if (isConnected) {
            socket.emit(Socket.EVENT_CONNECT, roomId);
        }
    }

    public void sendMessage(String message) {
        if (isConnected) {
            socket.emit(Socket.EVENT_MESSAGE, message);
        }
    }

    public void disConnect() {
        if (isConnected) {
            socket.disconnect();
        }
    }
}