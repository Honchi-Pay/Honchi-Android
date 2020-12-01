package com.honchipay.honchi_android.chat.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.MessageResponse;

class ChatBubbleViewHolder extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;

    public ChatBubbleViewHolder(View itemView) {
        super(itemView);
    }

    public void makeTextView(MessageResponse messageResponse) {
        if (messageResponse.isMine()) {
            makeBubbleView(R.layout.layout_right_bubble_chat, messageResponse);
        } else {
            makeBubbleView(R.layout.layout_left_bubble_chat, messageResponse);
        }
    }

    private void makeBubbleView(int directionLayoutResourceId, MessageResponse messageResponse) {
        linearLayout = new LinearLayout(itemView.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.addView(LayoutInflater.from(itemView.getContext()).inflate(directionLayoutResourceId, linearLayout, false));
        setValuesByDirection(messageResponse);
        ((LinearLayout) itemView.findViewById(R.id.item_bubble_own_layout)).addView(linearLayout);
    }

    private void setValuesByDirection(MessageResponse messageResponse) {
        if (messageResponse.isMine()) {
            setTextViewInRightBubble(messageResponse);
        } else {
            setTextViewInLeftBubble(messageResponse);
        }
    }

    private void setTextViewInRightBubble(MessageResponse messageResponse) {
        setMessageByIsDelete(messageResponse.isDelete(), messageResponse.getMessage(), R.id.right_bubble_message_textView);
        ((TextView) linearLayout.findViewById(R.id.right_bubble_userName_textView)).setText(messageResponse.getName());
        ((TextView) linearLayout.findViewById(R.id.right_bubble_time_textView)).setText(messageResponse.getTime().toString());
//        ((TextView) linearLayout.findViewById(R.id.right_bubble_readCount_textView)).setText(messageResponse);
    }

    private void setTextViewInLeftBubble(MessageResponse messageResponse) {
        setMessageByIsDelete(messageResponse.isDelete(), messageResponse.getMessage(), R.id.left_bubble_message_textView);
        ((TextView) linearLayout.findViewById(R.id.left_bubble_userName_textView)).setText(messageResponse.getName());
        ((TextView) linearLayout.findViewById(R.id.left_bubble_time_textView)).setText(messageResponse.getTime().toString());
//        ((TextView) linearLayout.findViewById(R.id.left_bubble_readCount_textView)).setText(messageResponse);
    }

    private void setMessageByIsDelete(boolean isDelete, String message, int bubbleMessageTextViewResourceId) {
        if (isDelete) {
            ((TextView) linearLayout.findViewById(bubbleMessageTextViewResourceId)).setText("삭제된 메시지입니다.");
        } else {
            ((TextView) linearLayout.findViewById(bubbleMessageTextViewResourceId)).setText(message);
        }
    }
}