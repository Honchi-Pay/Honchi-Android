package com.honchipay.honchi_android.chat.model;

import java.io.Serializable;

public class ChatListItem implements Serializable {
    String roomId;
    String title;
    int people;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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
}
