package com.honchipay.honchi_android.chat;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.honchipay.honchi_android.chat.model.socket.ChangeTitleRequest;
import com.honchipay.honchi_android.chat.model.socket.GetPriceRequest;
import com.honchipay.honchi_android.chat.model.socket.ImageRequest;
import com.honchipay.honchi_android.chat.model.socket.MessageRequest;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class HonchiPaySocket {
    private static final String TAG = HonchiPaySocket.class.getSimpleName();
    private static final String SERVER_URL = "http://13.124.126.208:8000?token="
            + SharedPreferencesManager.getInstance().getAccessToken().substring(7);
    private static HonchiPaySocket single_instance = null;
    private boolean isConnected = false;
    public Integer postId = null;
    public Socket socket;

    public static HonchiPaySocket getInstance() {
        if (single_instance == null) {
            single_instance = new HonchiPaySocket();
        }

        return single_instance;
    }

    public void connect() {
        try {
            if (!isConnected) {
                socket = IO.socket(SERVER_URL);
                socket.connect();
                isConnected = true;
                printLog("success connected");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void createChatRoom() {
        if (isConnected && postId != null) {
            socket.emit("joinRoom", postId.toString());
            printLog("createChatRoom");
        }
    }

    public void joinIntoRoom(String chatId) {
        if (isConnected) {
            socket.emit("joinRoom", chatId);
            printLog("joinIntoRoom");
        }
    }

    public void leaveRoom(String chatId) {
        if (isConnected) {
            socket.emit("leaveRoom", chatId);
            printLog("leaveRoom");
        }
    }

    public void changeRoomTitle(ChangeTitleRequest changeTitleRequest) {
        if (isConnected) {
            try {
                String json = new Gson().toJson(changeTitleRequest);
                printLog(json);
                JSONObject obj = new JSONObject(json);
                socket.emit("changeTitle", obj);
                printLog("changeRoomTitle");
            } catch (JSONException e) {
                printLog(e.getMessage());
            }
        }
    }

    public void sendMessage(MessageRequest messageRequest) {
        if (isConnected) {
            try {
                String json = new Gson().toJson(messageRequest);
                printLog(json);
                JSONObject obj = new JSONObject(json);
                socket.emit("sendMessage", obj);
                printLog("sendMessage");
            } catch (JSONException e) {
                printLog(e.getMessage());
            }
        }
    }

    public void sendImage(ImageRequest imageRequest) {
        if (isConnected) {
            try {
                String json = new Gson().toJson(imageRequest);
                printLog(json);
                JSONObject obj = new JSONObject(json);
                socket.emit("sendImage", obj);
                printLog("sendImage");
            } catch (JSONException e) {
                printLog(e.getMessage());
            }
        }
    }

    public void getPrice(GetPriceRequest getPriceRequest) {
        if (isConnected) {
            try {
                String json = new Gson().toJson(getPriceRequest);
                printLog(json);
                JSONObject obj = new JSONObject(json);
                socket.emit("getPrice", obj);
                printLog("getPrice");
            } catch (JSONException e) {
                printLog(e.getMessage());
            }
        }
    }

    public void disConnect() {
        if (isConnected) {
            socket.disconnect();
            isConnected = false;
            printLog("disConnect");
        }
    }

    private void printLog(String message) {
        Log.e(TAG, message);
    }
}