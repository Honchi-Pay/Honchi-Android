package com.honchipay.honchi_android.chat.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.HonchiPaySocket;
import com.honchipay.honchi_android.chat.viewModel.ChatViewModel;

public class ChatListFragment extends Fragment {
    ChatViewModel chatViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        HonchiPaySocket.getInstance().connectSocket();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        chatViewModel.getParticipatingChatRooms();
        chatViewModel.chatRoomListLiveData.observe(getViewLifecycleOwner(), chatListItems -> {

        });
    }
}