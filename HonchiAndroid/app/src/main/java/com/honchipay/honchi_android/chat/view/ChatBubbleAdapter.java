package com.honchipay.honchi_android.chat.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.ChattingContent;
import com.honchipay.honchi_android.databinding.ItemChatBubbleBinding;

import java.util.List;

public class ChatBubbleAdapter extends RecyclerView.Adapter<ChatBubbleAdapter.ChatBubbleViewHolder> {
    List<ChattingContent> chattingContentList;

    ChatBubbleAdapter(List<ChattingContent> contentList) {
        chattingContentList = contentList;
    }

    @NonNull
    @Override
    public ChatBubbleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatBubbleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_bubble, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatBubbleViewHolder holder, int position) {
        ChattingContent chattingContent = chattingContentList.get(position);
    }

    @Override
    public int getItemCount() {
        return chattingContentList.size();
    }

    static class ChatBubbleViewHolder extends RecyclerView.ViewHolder {
        ItemChatBubbleBinding binding;

        public ChatBubbleViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
