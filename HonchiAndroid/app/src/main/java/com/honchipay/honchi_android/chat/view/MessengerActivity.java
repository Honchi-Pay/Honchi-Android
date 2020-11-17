package com.honchipay.honchi_android.chat.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.HonchiPaySocket;
import com.honchipay.honchi_android.chat.model.ChatListItem;
import com.honchipay.honchi_android.chat.viewModel.ChatViewModel;
import com.honchipay.honchi_android.databinding.ActivityMessengerBinding;

public class MessengerActivity extends AppCompatActivity {
    ChatListItem chatRoomData;
    String roomTitle;
    ChatViewModel chatViewModel;
    ActivityMessengerBinding binding;
    int PICTURES_REQUEST_CODE = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setEditToChangeRoomTitle();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_messenger);
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        chatRoomData = (ChatListItem) getIntent().getExtras().getSerializable("chatData");
        binding.setRoomTitle(chatRoomData.getTitle());
        roomTitle = chatRoomData.getTitle();
        HonchiPaySocket.getInstance().joinIntoRoom(chatRoomData.getRoomId());
    }

    private void setEditToChangeRoomTitle() {
        binding.messengerInputMessageEditText.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                String changeTitle = ((EditText) v).getText().toString();

                if (!changeTitle.equals("") && !changeTitle.equals(roomTitle)) {
                    chatViewModel.changeChatRoomTitle(changeTitle);
                }

                return true;
            }
            return false;
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
        HonchiPaySocket.getInstance().disConnect();
    }
}