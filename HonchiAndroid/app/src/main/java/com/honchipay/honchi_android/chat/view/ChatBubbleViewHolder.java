package com.honchipay.honchi_android.chat.view;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.model.MessageResponse;
import com.honchipay.honchi_android.chat.viewModel.ChatViewModel;

class ChatBubbleViewHolder extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;
    LinearLayout rootLayout;

    public ChatBubbleViewHolder(View itemView) {
        super(itemView);
        rootLayout = itemView.findViewById(R.id.item_bubble_own_layout);
    }

    public void setLongClickOnView(ChatViewModel chatViewModel, int messageId) {
        itemView.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("메시지 삭제하시겠습니까?");
            builder.setPositiveButton("네", (dialog, id) -> {
                chatViewModel.deleteMessage(messageId);
                dialog.dismiss();
            });
            builder.setNegativeButton("아니요", (dialog, id) -> dialog.dismiss());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            return true;
        });
    }

    public void makeTextView(MessageResponse messageResponse) {
        if (messageResponse.isMine()) {
            makeBubbleView(R.layout.layout_right_bubble_chat_message);
            setTextViewInRightBubble(messageResponse);
        } else {
            makeBubbleView(R.layout.layout_left_bubble_chat_message);
            setTextViewInLeftBubble(messageResponse);
        }
    }

    private void makeBubbleView(int directionLayoutResourceId) {
        linearLayout = new LinearLayout(itemView.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.addView(LayoutInflater.from(itemView.getContext()).inflate(directionLayoutResourceId, linearLayout, false));
        rootLayout.addView(linearLayout);
    }

    private void setTextViewInRightBubble(MessageResponse messageResponse) {
        setMessageByIsDelete(messageResponse.isDelete(), messageResponse.getMessage(), R.id.right_bubble_message_textView);
        ((TextView) linearLayout.findViewById(R.id.right_bubble_userName_textView)).setText(messageResponse.getNickName());
        ((TextView) linearLayout.findViewById(R.id.right_bubble_time_textView)).setText(messageResponse.getTime().toString());
        ((TextView) linearLayout.findViewById(R.id.right_bubble_readCount_textView)).setText(messageResponse.getReadCount());
    }

    private void setTextViewInLeftBubble(MessageResponse messageResponse) {
        setMessageByIsDelete(messageResponse.isDelete(), messageResponse.getMessage(), R.id.left_bubble_message_textView);
        ((TextView) linearLayout.findViewById(R.id.left_bubble_userName_textView)).setText(messageResponse.getNickName());
        ((TextView) linearLayout.findViewById(R.id.left_bubble_time_textView)).setText(messageResponse.getTime().toString());
        ((TextView) linearLayout.findViewById(R.id.left_bubble_readCount_textView)).setText(messageResponse.getReadCount());
    }

    private void setMessageByIsDelete(boolean isDelete, String message, int bubbleMessageTextViewResourceId) {
        if (isDelete) {
            ((TextView) linearLayout.findViewById(bubbleMessageTextViewResourceId)).setText("삭제된 메시지입니다.");
        } else {
            ((TextView) linearLayout.findViewById(bubbleMessageTextViewResourceId)).setText(message);
        }
    }


    public void makeImageView(MessageResponse messageResponse) {
        if (messageResponse.isMine()) {
            makeBubbleImageView(R.layout.layout_right_bubble_chat_image);
            setImageViewInRightBubble(messageResponse);
        } else {
            makeBubbleImageView(R.layout.layout_left_bubble_chat_image);
            setImageViewInLeftBubble(messageResponse);
        }
    }

    private void makeBubbleImageView(int directionLayoutResourceId) {
        linearLayout = new LinearLayout(itemView.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.addView(LayoutInflater.from(itemView.getContext()).inflate(directionLayoutResourceId, linearLayout, false));
        rootLayout.addView(linearLayout);
    }

    private void setImageViewInRightBubble(MessageResponse messageResponse) {
        setImageByIsDelete(messageResponse.isDelete(), messageResponse.getMessage(), R.id.right_bubble_image_imageView);
        ((TextView) linearLayout.findViewById(R.id.right_bubble_image_userName_textView)).setText(messageResponse.getNickName());
        ((TextView) linearLayout.findViewById(R.id.right_bubble_image_time_textView)).setText(messageResponse.getTime().toString());
        ((TextView) linearLayout.findViewById(R.id.right_bubble_image_readCount_textView)).setText(messageResponse.getReadCount());
    }

    private void setImageViewInLeftBubble(MessageResponse messageResponse) {
        setImageByIsDelete(messageResponse.isDelete(), messageResponse.getMessage(), R.id.left_bubble_image_imageView);
        ((TextView) linearLayout.findViewById(R.id.left_bubble_image_userName_textView)).setText(messageResponse.getNickName());
        ((TextView) linearLayout.findViewById(R.id.left_bubble_image_time_textView)).setText(messageResponse.getTime().toString());
        ((TextView) linearLayout.findViewById(R.id.left_bubble_image_readCount_textView)).setText(messageResponse.getReadCount());
    }

    private void setImageByIsDelete(boolean isDelete, String message, int bubbleMessageImageViewResourceId) {
        ImageView imageView = linearLayout.findViewById(bubbleMessageImageViewResourceId);

        if (isDelete) {
            imageView.setImageDrawable(null);
        } else {
            Glide.with(itemView.getContext()).load(message).into(imageView);
        }
    }

    public void showInformation(String message) {
        linearLayout = new LinearLayout(itemView.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(itemView.getContext());
        textView.setText(message);
        linearLayout.addView(textView);
        rootLayout.addView(linearLayout);
    }
}