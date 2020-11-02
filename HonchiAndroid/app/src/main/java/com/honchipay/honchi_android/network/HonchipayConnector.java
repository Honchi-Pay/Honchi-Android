
package com.honchipay.honchi_android.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HonchipayConnector {
    HonchipayApi api;
    String baseURL = "";

    private static HonchipayConnector single_instance = null;

    private HonchipayConnector() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        api = retrofit.create(HonchipayApi.class);
    }

    public static HonchipayConnector getInstance() {
        if (single_instance == null) {
            single_instance = new HonchipayConnector();
        }

        return single_instance;
    }

    public HonchipayApi getApi() {
        return api;
    }
}