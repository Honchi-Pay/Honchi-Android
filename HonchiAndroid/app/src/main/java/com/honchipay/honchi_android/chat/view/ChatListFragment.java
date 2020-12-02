package com.honchipay.honchi_android.chat.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.chat.HonchiPaySocket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.chat.model.MessageResponse;
import com.honchipay.honchi_android.chat.viewModel.ChatViewModel;

import io.socket.emitter.Emitter;

public class ChatListFragment extends Fragment {
    ChatViewModel chatViewModel;
    RecyclerView recyclerView;
    ChatRoomsAdapter chatRoomsAdapter;
    final Emitter.Listener newChatRoomListener = args -> {
        HonchiPaySocket.getInstance().messageResponse = (MessageResponse) args[0];
        Intent intent = new Intent(requireContext(), MessengerActivity.class);
        requireContext().startActivity(intent);
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
            HonchiPaySocket.getInstance().connect();
            HonchiPaySocket.getInstance().socket.on("info", newChatRoomListener);
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        HonchiPaySocket.getInstance().socket.off("info", newChatRoomListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HonchiPaySocket.getInstance().disConnect();
    }
}