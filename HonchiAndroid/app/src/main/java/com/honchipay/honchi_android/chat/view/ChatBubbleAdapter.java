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
        holder.makeTextView(messageResponse);

        holder.itemView.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("메시지 삭제하시겠습니까?");
            builder.setPositiveButton("네", (dialog, id) -> {
                chatViewModel.deleteMessage(messageResponse.getId());
                dialog.dismiss();
            });
            builder.setNegativeButton("아니요", (dialog, id) -> dialog.dismiss());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            return true;
        });
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
