package com.honchipay.honchi_android.chat.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.MessageResponse;
import com.honchipay.honchi_android.chat.viewModel.ChatViewModel;

import java.util.List;

public class ChatBubbleAdapter extends RecyclerView.Adapter<ChatBubbleViewHolder> {
    List<MessageResponse> chattingContentList;
    ChatViewModel chatViewModel;

    ChatBubbleAdapter(List<MessageResponse> contentList, ChatViewModel chatViewModel) {
        chattingContentList = contentList;
        this.chatViewModel = chatViewModel;
    }

    @NonNull
    @Override
    public ChatBubbleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatBubbleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_bubble, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatBubbleViewHolder holder, int position) {
        MessageResponse messageResponse = chattingContentList.get(position);
        switch (messageResponse.getMessageType()) {
            case MESSAGE:
                holder.makeTextView(messageResponse);
                holder.setLongClickOnView(chatViewModel, messageResponse.getId());
                break;
            case IMAGE:
                holder.makeImageView(messageResponse);
                holder.setLongClickOnView(chatViewModel, messageResponse.getId());
                break;
            case INFO:
                holder.showInformation(messageResponse.getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return chattingContentList.size();
    }

    public void setAllMessages(List<MessageResponse> messages) {
        chattingContentList.clear();
        chattingContentList.addAll(messages);
        notifyDataSetChanged();
    }

    public void addMessage(MessageResponse message) {
        chattingContentList.add(chattingContentList.size(), message);
        notifyDataSetChanged();
    }
}
