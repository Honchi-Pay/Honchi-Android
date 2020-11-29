package com.honchipay.honchi_android.network;

import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.chat.model.MessageResponse;
import com.honchipay.honchi_android.profile.data.UserProfileResponse;
import com.honchipay.honchi_android.sign.data.SignUpRequest;
import com.honchipay.honchi_android.sign.data.TokenResponseData;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import okhttp3.RequestBody;
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
import retrofit2.http.Query;

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
    Single<Response<UserProfileResponse>> getUserProfile(@Header("Authorization") String header, @Query("nickName") String name);

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

    @GET("/chat")
    Single<Response<List<ChatRoomItem>>> getChatRooms(@Header("Authorization") String header);

    @PUT("/chat/{roomId}")
    Single<Response<Void>> updateChatRoomTitle(@Header("Authorization") String header, @Path("roomId") String roomId, @Body HashMap<String, String> body);

    @GET("/message/{chatId}")
    Single<Response<List<MessageResponse>>> getAllMessages(@Header("Authorization") String header, @Path("chatId") String chatId);
}