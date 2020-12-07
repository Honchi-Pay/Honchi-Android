package com.honchipay.honchi_android.home.data;


import java.util.ArrayList;

public class newPost {
    int postId;
    String title;
    String writer;
    String item;
    String image;
    String address;
    ArrayList<Integer> createdAt;

    public newPost(int postId, String title, String writer, String item, String image, String address, ArrayList<Integer> createdAt) {
        this.postId = postId;
        this.title = title;
        this.writer = writer;
        this.item = item;
        this.image = image;
        this.address = address;
        this.createdAt = createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ArrayList<Integer> getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ArrayList<Integer> createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}