package com.honchipay.honchi_android.home.Data;

public class detailPostItem {
    String content;
    String title;
    String people;
    String location;
    String date;
    String time;
    String category;

    public detailPostItem(String content, String title, String people, String location, String date, String time, String category) {
        this.content = content;
        this.title = title;
        this.people = people;
        this.location = location;
        this.date = date;
        this.time = time;
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
