package com.honchipay.honchi_android.chat.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.databinding.ItemChatRoomsBinding;

import java.util.Collections;
import java.util.List;

public class ChatRoomsAdapter extends RecyclerView.Adapter<ChatRoomsAdapter.ChatRoomsViewHolder> {
    private final List<ChatRoomItem> chatRoomItems;

    public ChatRoomsAdapter() {
        chatRoomItems = Collections.emptyList();
    }

    @NonNull
    @Override
    public ChatRoomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatRoomsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_rooms, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomsViewHolder holder, int position) {
        ChatRoomItem chatRoomItem = chatRoomItems.get(position);
        holder.binding.setChatRoomItem(chatRoomItem);
        holder.itemView.setOnClickListener(v -> holder.onClick(chatRoomItem));
    }

    @Override
    public int getItemCount() {
        return chatRoomItems.size();
    }

    public void renewChatList(List<ChatRoomItem> list) {
        chatRoomItems.clear();
        chatRoomItems.addAll(list);
        notifyDataSetChanged();
    }

    static class ChatRoomsViewHolder extends RecyclerView.ViewHolder {
        ItemChatRoomsBinding binding;

        public ChatRoomsViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void onClick(ChatRoomItem item) {
            Intent intent = new Intent(itemView.getContext(), MessengerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("chatData", item);
            intent.putExtras(bundle);
            itemView.getContext().startActivity(intent);
        }
    }
}
