package com.honchipay.honchi_android.chat.model;

import com.honchipay.honchi_android.base.BaseRepository;
import com.honchipay.honchi_android.network.HonchipayConnector;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ChatRepository extends BaseRepository {
    public Disposable changeRoomTitle(String roomId, String title, DisposableSingleObserver<Response<Void>> roomTitleObserver) {
        HashMap<String, String> body = new HashMap<>();
        body.put("title", title);

        return wrappingSingle(HonchipayConnector.getInstance().getApi().updateChatRoomTitle(token, roomId, body), roomTitleObserver);
    }

    public Disposable getChatRooms(DisposableSingleObserver<Response<List<ChatRoomItem>>> chatRoomsObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().getChatRooms(token), chatRoomsObserver);
    }

    public Disposable getAllMessages(String roomId, DisposableSingleObserver<Response<List<MessageResponse>>> chatMessageObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().getAllMessages(token, roomId), chatMessageObserver);
    }

    public Disposable deleteMessage(int chatId, DisposableSingleObserver<Response<Void>> deleteMessageObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().deleteMessage(token, chatId), deleteMessageObserver);
    }

    public Disposable readMessages(String roomId, DisposableSingleObserver<Response<Void>> readMessageObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().readMessages(token, roomId), readMessageObserver);
    }

    public Disposable uploadImage(String chatId, File imageFile, DisposableSingleObserver<Response<MessageIdResponse>> uploadImageObserver) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("chatId", chatId)
                .build();

        RequestBody rqFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part mpFile = MultipartBody.Part.createFormData("image", imageFile.getName(), rqFile);

        return wrappingSingle(HonchipayConnector.getInstance().getApi().uploadImage(token, requestBody, mpFile), uploadImageObserver);
    }
}
