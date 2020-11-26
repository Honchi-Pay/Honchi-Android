package com.honchipay.honchi_android.chat.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.ChattingContent;

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
//        if (chattingContent.getContent() != null) {
//            holder.makeTextView(chattingContent.getContent());
//        } else {
//            holder.makeImageView(chattingContent.getImg());
//        }
    }

    @Override
    public int getItemCount() {
        return chattingContentList.size();
    }

    public void addMessage(String message) {
        ChattingContent chattingContent = new ChattingContent();
        chattingContent.setContent(message);
        addToContentList(chattingContent);
    }

    public void addImage(String image) {
        ChattingContent chattingContent = new ChattingContent();
        chattingContent.setImg(image);
        addToContentList(chattingContent);
    }

    private void addToContentList(ChattingContent chattingContent) {
        chattingContentList.add(chattingContentList.size(), chattingContent);
        notifyDataSetChanged();
    }

    static class ChatBubbleViewHolder extends RecyclerView.ViewHolder {
        public ChatBubbleViewHolder(View itemView) {
            super(itemView);
        }

        public void makeTextView(String message, boolean isMine) {
            TextView textView = new TextView(itemView.getContext());
            textView.setText(message);
            textView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.background_horizontal_bar));

            LinearLayout linearLayout = new LinearLayout(itemView.getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            if (isMine) {
                textView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#79A5DF")));
                layoutParams.gravity = Gravity.END;
            } else {
                textView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                layoutParams.gravity = Gravity.START;
            }

            linearLayout.setLayoutParams(layoutParams);
            linearLayout.addView(textView);
            ((LinearLayout) itemView.findViewById(R.id.item_bubble_own_layout)).addView(linearLayout);
        }

        public void makeImageView(String imageUrl, boolean isMine) {
            ImageView imageView = new ImageView(itemView.getContext());
            imageView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.background_horizontal_bar));
            imageView.setClipToOutline(true);

            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
            LinearLayout linearLayout = new LinearLayout(itemView.getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            if (isMine) {
                layoutParams.gravity = Gravity.END;
            } else {
                layoutParams.gravity = Gravity.START;
            }

            linearLayout.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);
            ((LinearLayout) itemView.findViewById(R.id.item_bubble_own_layout)).addView(linearLayout);
        }
    }
}
