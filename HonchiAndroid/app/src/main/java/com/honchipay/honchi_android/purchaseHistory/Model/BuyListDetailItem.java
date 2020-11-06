package com.honchipay.honchi_android.purchaseHistory.Model;


public class BuyListDetailItem {
    int post_id;
    String title;
    String writer;
    String created_at;
    String price;

    public int getPost_id() {
        return post_id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getPrice() {
        return price;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}