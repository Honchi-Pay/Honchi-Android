package com.honchipay.honchi_android.chat.model;

import com.honchipay.honchi_android.base.BaseRepository;
import com.honchipay.honchi_android.network.HonchipayConnector;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ChatRepository extends BaseRepository {
    public Disposable changeRoomTitle(String title, DisposableSingleObserver<Response<Void>> roomTitleObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().updateChatRoomTitle(title), roomTitleObserver);
    }

    public Disposable getChatRooms(DisposableSingleObserver<Response<List<ChatRoomItem>>> chatRoomsObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().getChatRooms(token), chatRoomsObserver);
    }
}
