package com.honchipay.honchi_android.chat.model;

import com.honchipay.honchi_android.base.BaseRepository;
import com.honchipay.honchi_android.network.HonchipayConnector;

import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
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

    public Disposable getAllMessages(String roomId, DisposableSingleObserver<Response<List<MessageResponseByServer>>> chatMessageObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().getAllMessages(token, roomId), chatMessageObserver);
    }

    public Disposable deleteMessage(int chatId, DisposableSingleObserver<Response<Void>> deleteMessageObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().deleteMessage(token, chatId), deleteMessageObserver);
    }

    public Disposable readMessages(String roomId, DisposableSingleObserver<Response<Void>> readMessageObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().readMessages(token, roomId), readMessageObserver);
    }
}
