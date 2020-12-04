package com.honchipay.honchi_android.chat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomItem implements Serializable {
    private String chatId;
    private String title;
    private int people;
    private String message;
    private ArrayList<String> images;

    public static class Builder {
        private String chatId;
        private String title;
        private int people = 1;
        private String message = "";
        private ArrayList<String> images = new ArrayList<>();

        public Builder(String chatId, String title) {
            this.chatId = chatId;
            this.title = title;
        }

        public Builder people(int people) {
            this.people = people;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }
        public Builder images(ArrayList<String> images) {
            this.images = images;
            return this;
        }

        public ChatRoomItem build() {
            return new ChatRoomItem(this);
        }
    }

    private ChatRoomItem(Builder builder) {
        chatId = builder.chatId;
        title = builder.title;
        people = builder.people;
        message = builder.message;
        images = builder.images;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String roomId) {
        this.chatId = roomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImages() {
        if (images.size() == 0) {
            return null;
        } else {
            return images.get(0);
        }
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}