package com.honchipay.honchi_android.writing;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.util.HashMap;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class writingRepository {
    String testToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDcyMzUwNDgsImV4cCI6MTYwNzgzOTg0OCwic3ViIjoibWFyYmxpbmcxMjkzQGRzbS5ocy5rciIsInR5cGUiOiJyZWZyZXNoX3Rva2VuIn0.8c36-K9xnEUZjTSZQbTFPhn3PePpIGxuOb5L0qHfDcY";

    public Single<Response<Void>> writing(
            MultipartBody.Part titlePart,
            MultipartBody.Part contentPart,
            MultipartBody.Part imagesPart,
            MultipartBody.Part categoryPart,
            MultipartBody.Part itemPart,
            MultipartBody.Part latPart,
            MultipartBody.Part lonPart
            ) {
        //String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().writing(testToken,titlePart, contentPart,imagesPart,categoryPart,itemPart,latPart,lonPart);
    }

    public Single<Response<Void>> modifyPost(){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().modifyPost(token);
    }

    public Single<Response<Void>> deletePost(){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().deletePost(token);
    }
}
