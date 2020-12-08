package com.honchipay.honchi_android.home.Data;

public class getPost {
    int postId;
    String title;
    String writer;
    String image;
    String createAt;
    String address;

    public getPost(int postId, String title, String writer, String image, String createAt, String address) {
        this.postId = postId;
        this.title = title;
        this.writer = writer;
        this.image = image;
        this.createAt = createAt;
        this.address = address;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
