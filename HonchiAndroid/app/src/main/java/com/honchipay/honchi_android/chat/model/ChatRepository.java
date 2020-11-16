package com.honchipay.honchi_android.chat.model;

import com.honchipay.honchi_android.base.BaseRepository;
import com.honchipay.honchi_android.network.HonchipayConnector;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public class ChatRepository extends BaseRepository {
    public Single<Response<List<ChatListItem>>> getChatList() {
        return HonchipayConnector.getInstance().getApi().getChatList(token);
    }

    public Single<Response<Void>> changeRoomTitle(String title) {
        return HonchipayConnector.getInstance().getApi().updateChatRoomTitle(title);
    }
}
