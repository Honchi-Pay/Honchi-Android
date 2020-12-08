package com.honchipay.honchi_android.network;

import com.honchipay.honchi_android.buyList.BuyListResponse;
import com.honchipay.honchi_android.home.Data.detailPost;
import com.honchipay.honchi_android.home.Data.getPost;
import com.honchipay.honchi_android.home.Data.newPost;
import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.chat.model.MessageIdResponse;
import com.honchipay.honchi_android.chat.model.MessageResponse;
import com.honchipay.honchi_android.profile.data.UserProfileResponse;
import com.honchipay.honchi_android.sign.data.SignUpRequest;
import com.honchipay.honchi_android.sign.data.TokenResponseData;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
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
import retrofit2.http.Part;
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

    @GET("/user/profile")
    Single<Response<UserProfileResponse>> getUserProfile(@Header("Authorization") String header, @Query(value = "nickName", encoded = true) String name);

    @Multipart
    @PUT("/user/profile")
    Single<Response<Void>> updateUserProfile(@Header("Authorization") String header, @Part("nickName") RequestBody name, @Part MultipartBody.Part file);

    @PUT("/user/password/change")
    Single<Response<Void>> changePassword(@Header("Authorization") String header, @Body HashMap<String, String> body);

    @POST("/user/star")
    Single<Response<Void>> sendUserEvaluation(@Header("Authorization") String header, @Body HashMap<String, Integer> body);

    @FormUrlEncoded
    @DELETE("/user/out")
    Single<Response<Void>> withdrawFromService(@Header("Authorization") String header, @Field("nickName") String name);

    @GET("/post/recent")
    Single<Response<List<newPost>>> getNewPost(@Header("Authorization") String header, @Query("category") String category);

    @GET("/post")
    Single<Response<List<getPost>>> getPostList(
            @Header("Authorization") String header,
            @Query("category") String category,
            @Query ("item") String item,
            @Query("dist") int dist);

    @Multipart
    @POST("/post")
    Single<Response<Void>> writing(
            @Header("Authorization") String header,
            @Part MultipartBody.Part title,
            @Part MultipartBody.Part content,
            @Part MultipartBody.Part images,
            @Part MultipartBody.Part category,
            @Part MultipartBody.Part item,
            @Part MultipartBody.Part lat,
            @Part MultipartBody.Part lon
    );

    @PUT("/post/{postId}")
    Single<Response<Void>> modifyPost(
            @Header("Authorization") String header
    );

    @DELETE("/post/{postId}")
    Single<Response<Void>> deletePost(
            @Header("Authorization") String header
    );

    @GET("/post/search")
    Single<Response<List<newPost>>> searchPost(
            @Header("Authorization") String header,
            @Query("title") String title,
            @Query("dist") int dist
    );

    @GET("post/{postID}")
    Single<Response<detailPost>> detailPost(
            @Header("Authorization") String header,
            @Path("postID") int postID
    );

    @GET("/chat")
    Single<Response<List<ChatRoomItem>>> getChatRooms(@Header("Authorization") String header);

    @POST("/message")
    Single<Response<MessageIdResponse>> uploadImage(@Header("Authorization") String header, @Part("chatId") RequestBody chatId, @Part MultipartBody.Part file);

    @GET("/message/{chatId}")
    Single<Response<List<MessageResponse>>> getAllMessages(@Header("Authorization") String header, @Path("chatId") String chatId);

    @PUT("/message/{chatId}")
    Single<Response<Void>> readMessages(@Header("Authorization") String header, @Path("chatId") String chatId);

    @PUT("/message/{messageId}")
    Single<Response<Void>> deleteMessage(@Header("Authorization") String header, @Path("messageId") int messageId);

    @PUT("/chat/{roomId}")
    Single<Response<Void>> updateChatRoomTitle(@Header("Authorization") String header, @Path("roomId") String roomId, @Body HashMap<String, String> body);

    @GET("/buyList")
    Single<Response<List<BuyListResponse>>> getBuyList(
            @Header("Authorization") String header
    );
}