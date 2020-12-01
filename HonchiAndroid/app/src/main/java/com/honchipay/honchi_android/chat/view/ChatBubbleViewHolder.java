package com.honchipay.honchi_android.chat.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.MessageResponseByServer;

class ChatBubbleViewHolder extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;

    public ChatBubbleViewHolder(View itemView) {
        super(itemView);
    }

    public void makeTextView(MessageResponseByServer messageResponseByServer) {
        if (messageResponseByServer.isMine()) {
            makeBubbleView(R.layout.layout_right_bubble_chat, messageResponseByServer);
        } else {
            makeBubbleView(R.layout.layout_left_bubble_chat, messageResponseByServer);
        }
    }

    private void makeBubbleView(int directionLayoutResourceId, MessageResponseByServer messageResponseByServer) {
        linearLayout = new LinearLayout(itemView.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.addView(LayoutInflater.from(itemView.getContext()).inflate(directionLayoutResourceId, linearLayout, false));
        setValuesByDirection(messageResponseByServer);
        ((LinearLayout) itemView.findViewById(R.id.item_bubble_own_layout)).addView(linearLayout);
    }

    private void setValuesByDirection(MessageResponseByServer messageResponseByServer) {
        if (messageResponseByServer.isMine()) {
            setTextViewInRightBubble(messageResponseByServer);
        } else {
            setTextViewInLeftBubble(messageResponseByServer);
        }
    }

    private void setTextViewInRightBubble(MessageResponseByServer messageResponseByServer) {
        setMessageByIsDelete(messageResponseByServer.isDelete(), messageResponseByServer.getMessage(), R.id.right_bubble_message_textView);
        ((TextView) linearLayout.findViewById(R.id.right_bubble_userName_textView)).setText(messageResponseByServer.getNickName());
        ((TextView) linearLayout.findViewById(R.id.right_bubble_time_textView)).setText(messageResponseByServer.getTime().toString());
        ((TextView) linearLayout.findViewById(R.id.right_bubble_readCount_textView)).setText(messageResponseByServer.getReadCount());
    }

    private void setTextViewInLeftBubble(MessageResponseByServer messageResponseByServer) {
        setMessageByIsDelete(messageResponseByServer.isDelete(), messageResponseByServer.getMessage(), R.id.left_bubble_message_textView);
        ((TextView) linearLayout.findViewById(R.id.left_bubble_userName_textView)).setText(messageResponseByServer.getNickName());
        ((TextView) linearLayout.findViewById(R.id.left_bubble_time_textView)).setText(messageResponseByServer.getTime().toString());
        ((TextView) linearLayout.findViewById(R.id.left_bubble_readCount_textView)).setText(messageResponseByServer.getReadCount());
    }

    private void setMessageByIsDelete(boolean isDelete, String message, int bubbleMessageTextViewResourceId) {
        if (isDelete) {
            ((TextView) linearLayout.findViewById(bubbleMessageTextViewResourceId)).setText("삭제된 메시지입니다.");
        } else {
            ((TextView) linearLayout.findViewById(bubbleMessageTextViewResourceId)).setText(message);
        }
    }
}