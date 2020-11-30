package com.honchipay.honchi_android.chat;

import android.util.Log;

import com.honchipay.honchi_android.chat.model.MessageRequest;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class HonchiPaySocket {
    private static final String TAG = HonchiPaySocket.class.getSimpleName();
    private static final String SERVER_URL = "http://13.124.126.208:8000";
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
            IO.Options options = new IO.Options();
            options.query = "token=" + SharedPreferencesManager.getInstance().getAccessToken();
            socket = IO.socket(SERVER_URL, options);
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
            socket.emit("joinRoom", roomId);
        }
    }

    public void leaveRoom(String roomId) {
        if (isConnected) {
            socket.emit("leaveRoom", roomId);
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