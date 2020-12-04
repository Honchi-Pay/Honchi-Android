package com.honchipay.honchi_android.chat.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.honchipay.honchi_android.chat.HonchiPaySocket;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.chat.model.MessageResponse;
import com.honchipay.honchi_android.chat.viewModel.ChatViewModel;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import io.socket.emitter.Emitter;

public class ChatListFragment extends Fragment {
    ChatViewModel chatViewModel;
    RecyclerView recyclerView;
    ChatRoomsAdapter chatRoomsAdapter;
    final Emitter.Listener newChatRoomListener = args -> {
        ChatRoomItem chatRoomData = new ChatRoomItem.Builder(
                HonchiPaySocket.getInstance().postId.toString(),
                SharedPreferencesManager.getInstance().getUserName() + "님의 채팅방"
        ).build();

        chatRoomsAdapter.addChatItem(chatRoomData);
        HonchiPaySocket.getInstance().postId = null;
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HonchiPaySocket.getInstance().connect();
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chatRoomsAdapter = new ChatRoomsAdapter();
        recyclerView = view.findViewById(R.id.chat_list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(chatRoomsAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        chatViewModel.getParticipatingChatRooms();
        chatViewModel.chatRoomListLiveData.observe(getViewLifecycleOwner(), chatListItems -> {
            chatRoomsAdapter.renewChatList(chatListItems);

            HonchiPaySocket.getInstance().createChatRoom();
            HonchiPaySocket.getInstance().socket.on("info", newChatRoomListener);
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ChatListFragment", "onPause");
        HonchiPaySocket.getInstance().socket.off("info", newChatRoomListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HonchiPaySocket.getInstance().disConnect();
    }
}