package com.honchipay.honchi_android.chat.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.ChatListItem;
import com.honchipay.honchi_android.databinding.ItemChatListBinding;

import java.util.Collections;
import java.util.List;

public class ChatRoomListAdapter extends RecyclerView.Adapter<ChatRoomListAdapter.ChatRoomListViewHolder> {
    private final List<ChatListItem> chatListItems;

    public ChatRoomListAdapter() {
        chatListItems = Collections.emptyList();
    }

    @NonNull
    @Override
    public ChatRoomListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatRoomListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomListViewHolder holder, int position) {
        ChatListItem chatListItem = chatListItems.get(position);
        holder.binding.setChatRoomItem(chatListItem);
    }

    @Override
    public int getItemCount() {
        return chatListItems.size();
    }

    public void renewChatList(List<ChatListItem> list) {
        chatListItems.clear();
        chatListItems.addAll(list);
        notifyDataSetChanged();
    }

    static class ChatRoomListViewHolder extends RecyclerView.ViewHolder {
        ItemChatListBinding binding;

        public ChatRoomListViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
