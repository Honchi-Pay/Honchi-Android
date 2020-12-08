package com.honchipay.honchi_android.home.Data;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Response;

public class homeRepository {

    public Single<Response<List<newPost>>> getNewPost(String category){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().getNewPost(token,category);
    }

    public Single<Response<List<getPost>>> getPostList(String category,String item, int dist){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().getPostList(token, category, item, dist);
    }

    public Single<Response<List<newPost>>> searchPost(String title, int dist){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().searchPost(token,title,dist);
    }

    public Single<Response<detailPost>> detailPost(int postID){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().detailPost(token,postID);
    }

}