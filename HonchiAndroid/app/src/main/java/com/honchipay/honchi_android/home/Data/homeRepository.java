package com.honchipay.honchi_android.home.data;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Response;

public class homeRepository {
    String testToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDcyMzUwNDgsImV4cCI6MTYwNzgzOTg0OCwic3ViIjoibWFyYmxpbmcxMjkzQGRzbS5ocy5rciIsInR5cGUiOiJyZWZyZXNoX3Rva2VuIn0.8c36-K9xnEUZjTSZQbTFPhn3PePpIGxuOb5L0qHfDcY";

    public Single<Response<List<newPost>>> getNewPost(String category){
        //String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().getNewPost(testToken,category);
    }

    public Single<Response<List<getPost>>> getPostList(String category,String item, int dist){
        //String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().getPostList(testToken, category, item, dist);
    }

    public Single<Response<List<newPost>>> searchPost(String title, int dist){
        return HonchipayConnector.getInstance().getApi().searchPost(testToken,title,dist);
    }

    public Single<Response<detailPost>> detailPost(int postID){
        return HonchipayConnector.getInstance().getApi().detailPost(testToken,postID);
    }

}