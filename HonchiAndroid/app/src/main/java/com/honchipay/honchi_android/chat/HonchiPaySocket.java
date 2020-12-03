package com.honchipay.honchi_android.chat;

import android.util.Log;

import com.honchipay.honchi_android.chat.model.MessageRequest;
import com.honchipay.honchi_android.chat.model.MessageResponse;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class HonchiPaySocket {
    private static final String TAG = HonchiPaySocket.class.getSimpleName();
    private static final String SERVER_URL = "http://13.124.126.208:8000?token=" + SharedPreferencesManager.getInstance().getAccessToken().substring(7);
    private static HonchiPaySocket single_instance = null;
    private boolean isConnected = false;
    public MessageResponse messageResponse;
    public Integer postId = 4;
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
            createChatRoom();

            Log.e(TAG, "success connected");
        } catch (URISyntaxException e) {
            e.printStackTrace();

            isConnected = false;
        }
    }

    public void createChatRoom() {
        if (isConnected && postId != null) {
            socket.emit("joinRoom", postId.toString());
        }
    }

    public void joinIntoRoom(String chatId) {
        if (isConnected) {
            socket.emit("joinRoom", chatId);
        }
    }

    public void leaveRoom(String chatId) {
        if (isConnected) {
            socket.emit("leaveRoom", chatId);
        }
    }

    public void changeRoomTitle(String title) {
        if (isConnected) {
            socket.emit("changeTitle", title);
        }
    }

    public void sendMessage(MessageRequest message) {
        if (isConnected) {
            socket.emit("send", message);
        }
    }

    public void disConnect() {
        if (isConnected) {
            socket.disconnect();
        }
    }
}