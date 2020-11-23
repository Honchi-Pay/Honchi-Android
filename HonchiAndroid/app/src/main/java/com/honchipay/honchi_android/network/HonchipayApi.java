package com.honchipay.honchi_android.network;

import com.honchipay.honchi_android.base.ByteImage;
import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.profile.data.UserProfileResponse;
import com.honchipay.honchi_android.sign.Data.SignUpRequest;
import com.honchipay.honchi_android.sign.Data.TokenResponseData;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface HonchipayApi {
    @POST("/auth")
    Single<Response<TokenResponseData>> tryDoLogin(@Body HashMap<String, String> body);

    @PUT("/auth")
    Single<Response<TokenResponseData>> renewToken(@Body String refreshToken);

    @PUT("/user/email/verify")
    Single<Response<Void>> findPassword(@Body HashMap<String, String> body);

    @FormUrlEncoded
    @POST("/user/alone")
    Single<Response<Void>> checkFirstTimeUser(@Field("email") String email);

    @FormUrlEncoded
    @POST("/email/verify")
    Single<Response<Void>> checkDuplicatedEmail(@Field("email") String email);

    @PUT("/email/verify")
    Single<Response<Void>> checkAuthCode(@Body HashMap<String, String> body);

    @POST("/user")
    Single<Response<Void>> singUp(@Body SignUpRequest body);

    @FormUrlEncoded
    @GET("/user/profile")
    Single<Response<UserProfileResponse>> getUserProfile(@Header("Authorization") String header, @Field("nickName") String name);

    @Multipart
    @PUT("/user/profile")
    Single<Response<Void>> updateUserProfile(@Header("Authorization") String header, @PartMap HashMap<String, RequestBody> partMap);

    @PUT("/user/password/change")
    Single<Response<Void>> changePassword(@Header("Authorization") String header, @Body HashMap<String, String> body);

    @POST("/user/star")
    Single<Response<Void>> sendUserEvaluation(@Header("Authorization") String header, @Body HashMap<String, Integer> body);

    @FormUrlEncoded
    @DELETE("/user/out")
    Single<Response<Void>> withdrawFromService(@Header("Authorization") String header, @Field("nickName") String name);

    @GET("/image/<imageName>")
    Call<ByteImage> getImageByByte(@Header("Authorization") String header, @Path("imageName") String images);

    @GET("/chat")
    Single<Response<List<ChatRoomItem>>> getChatRooms(@Header("Authorization") String header);

    @PUT("/chat/{roomId}")
    Single<Response<Void>> updateChatRoomTitle(@Header("Authorization") String header, @Path("roomId") String roomId, @Body HashMap<String, String> body);
}