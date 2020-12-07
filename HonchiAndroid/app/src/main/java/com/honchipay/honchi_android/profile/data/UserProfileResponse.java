package com.honchipay.honchi_android.profile.data;

public class UserProfileResponse {
    private String email;
    private String nickName;
    private String sex;
    private double star;
    private String images;
    private boolean mine;
    private int userId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }


    public float getFloatStar() { return (float) star; }

    public String getStringStar() { return Double.toString(star); }
}
