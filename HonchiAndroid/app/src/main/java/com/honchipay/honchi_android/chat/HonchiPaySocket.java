package com.honchipay.honchi_android.chat;

import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class HonchiPaySocket {
    private static final String TAG = HonchiPaySocket.class.getSimpleName();
    private static final String SERVER_URL = "";
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

    public void sendParticipants(String[] participants) {
        if (isConnected) {
            socket.emit("", (Object) participants);
        }
    }

    public void joinIntoRoom(String roomId) {
        socket.emit("join", roomId);
    }

    public void sendMessage(String message) {
        socket.emit("", message);
    }

    public void disConnect() {
        if (isConnected) {
            socket.disconnect();
        }
    }
}