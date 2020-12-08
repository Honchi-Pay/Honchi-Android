package com.honchipay.honchi_android.chat.view;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.HonchiPaySocket;
import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.chat.model.MessageResponse;
import com.honchipay.honchi_android.chat.model.MessageResponseByTime;
import com.honchipay.honchi_android.chat.model.MessageType;
import com.honchipay.honchi_android.chat.model.socket.MessageRequest;
import com.honchipay.honchi_android.chat.viewModel.ChatViewModel;
import com.honchipay.honchi_android.databinding.ActivityMessengerBinding;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.io.File;
import java.util.Objects;

import io.socket.emitter.Emitter;

public class MessengerActivity extends AppCompatActivity {
    ChatRoomItem chatRoomData;
    ChatViewModel chatViewModel;
    ActivityMessengerBinding binding;
    RecyclerView recyclerView;
    ChatBubbleAdapter chatBubbleAdapter;

    final int PICTURES_REQUEST_CODE = 13;
    final Emitter.Listener messageListener = args -> {
        Log.e("MessengerActivity", "messageListener called");
        String json = String.valueOf(args[0]);
        Log.e("MessengerActivity", json);
        MessageResponseByTime messageResponseByTime = new Gson().fromJson(json, MessageResponseByTime.class);
        MessageResponse messageResponse = messageResponseByTime.converterOriginal();
        runOnUiThread(() -> chatBubbleAdapter.addMessage(messageResponse));
    };
    final Emitter.Listener infoListener = args -> {
        Log.e("MessengerActivity", "infoListener called");
        MessageResponse messageResponse = new Gson().fromJson(args[0].toString(), MessageResponse.class);
        Log.e("MessengerActivity", messageResponse.toString());
        runOnUiThread(() -> chatBubbleAdapter.addMessage(messageResponse));
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setRecyclerView();
        setSendMessage();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_messenger);

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        chatRoomData = (ChatRoomItem) getIntent().getExtras().getSerializable("chatData");
        chatViewModel.setRoomId(chatRoomData.getChatId());
        binding.messengerTitleNameTextView.setText(chatRoomData.getTitle());
        binding.messengerTitleNameTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                chatViewModel.changeChatRoomTitle(chatRoomData.getTitle(), s.toString());
            }
        });
        binding.setChatViewModel(chatViewModel);
    }

    private void setRecyclerView() {
        chatBubbleAdapter = new ChatBubbleAdapter(chatViewModel);
        recyclerView = binding.messengerMassagesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(MessengerActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatBubbleAdapter);

        chatViewModel.getAllMessages();
        chatViewModel.messageListLiveData.observe(this, allMessages -> {
            chatViewModel.readMessages();
            chatBubbleAdapter.setAllMessages(allMessages);
        });
    }

    private void setSendMessage() {
        HonchiPaySocket.getInstance().socket.on("receive", messageListener);
        HonchiPaySocket.getInstance().socket.on("info", infoListener);
        findViewById(R.id.messenger_sendMessage_imageView).setOnClickListener(v -> {
            String text = binding.messengerInputMessageEditText.getText().toString();
            binding.messengerInputMessageEditText.setText("");
            MessageRequest messageRequest = new MessageRequest();
            messageRequest.setChatId(chatRoomData.getChatId());
            messageRequest.setMessage(text);
            HonchiPaySocket.getInstance().sendMessage(messageRequest);
        });
    }

    @SuppressLint("IntentReset")
    public void selectImages(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICTURES_REQUEST_CODE);
    }

    public void pressBackButton(View view) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICTURES_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ClipData clipData = data.getClipData();
            Uri uri;

            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    uri = clipData.getItemAt(i).getUri();
                    chatViewModel.uploadImageToServer(chatRoomData.getChatId(), new File(String.valueOf(uri)));
                }
            } else {
                uri = data.getData();
                chatViewModel.uploadImageToServer(chatRoomData.getChatId(), new File(String.valueOf(uri)));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HonchiPaySocket.getInstance().socket.off("receive", messageListener);
        HonchiPaySocket.getInstance().socket.off("info", infoListener);
    }
}