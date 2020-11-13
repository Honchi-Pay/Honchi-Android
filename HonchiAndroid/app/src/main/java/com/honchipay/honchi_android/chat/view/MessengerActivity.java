package com.honchipay.honchi_android.chat.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.HonchiPaySocket;
import com.honchipay.honchi_android.chat.model.ChatListItem;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MessengerActivity extends AppCompatActivity {
    ChatListItem chatListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        chatListItem = (ChatListItem) getIntent().getExtras().getSerializable("chatData");
    }
}