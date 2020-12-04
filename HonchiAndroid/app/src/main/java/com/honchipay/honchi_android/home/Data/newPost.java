package com.honchipay.honchi_android.home.Data;

import com.google.android.gms.common.api.Result;

public class newPost {

    int postId;
    String title;
    String writer;
    String item;
    Double lat;
    Double lon;
    String image;
    String createdAt;


    public newPost(int postId, String title, String writer, String item, Double lat, Double lon, String createdAt, String image) {
        this.postId = postId;
        this.title = title;
        this.writer = writer;
        this.item = item;
        this.lat = lat;
        this.lon = lon;
        this.createdAt = createdAt;
        this.image = image;
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
