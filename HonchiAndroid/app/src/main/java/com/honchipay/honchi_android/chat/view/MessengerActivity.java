package com.honchipay.honchi_android.chat.view;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.HonchiPaySocket;
import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.chat.viewModel.ChatViewModel;
import com.honchipay.honchi_android.databinding.ActivityMessengerBinding;

import io.socket.emitter.Emitter;

import static java.util.Collections.emptyList;

public class MessengerActivity extends AppCompatActivity {
    ChatRoomItem chatRoomData;
    ChatViewModel chatViewModel;
    ActivityMessengerBinding binding;
    RecyclerView recyclerView;
    ChatBubbleAdapter chatBubbleAdapter;
    final int PICTURES_REQUEST_CODE = 13;
    final Emitter.Listener textListener = args -> chatBubbleAdapter.addMessage((String) args[0]);
    final Emitter.Listener imageListener = args -> chatBubbleAdapter.addMessage((String) args[0]);

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
        chatViewModel.setRoomId(chatRoomData.getRoomId());
        chatViewModel.roomTitle.set(chatRoomData.getTitle());
        chatViewModel.setRoomTitleValidation(chatRoomData.getTitle());

        binding.setChatViewModel(chatViewModel);
        HonchiPaySocket.getInstance().joinIntoRoom(chatRoomData.getRoomId());
        HonchiPaySocket.getInstance().socket.on("", textListener);
        HonchiPaySocket.getInstance().socket.on("", imageListener);
    }

    private void setRecyclerView() {
        chatBubbleAdapter = new ChatBubbleAdapter(emptyList());
        recyclerView = findViewById(R.id.messenger_massages_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatBubbleAdapter);
    }

    private void setSendMessage() {
        findViewById(R.id.messenger_sendMessage_imageView).setOnClickListener(v -> {
            String text = ((EditText) findViewById(R.id.messenger_inputMessage_editText)).getText().toString();
            HonchiPaySocket.getInstance().sendMessage(text);
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
                }
            } else {
                uri = data.getData();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HonchiPaySocket.getInstance().socket.off("", textListener);
        HonchiPaySocket.getInstance().socket.off("", imageListener);
        HonchiPaySocket.getInstance().disConnect();
    }
}