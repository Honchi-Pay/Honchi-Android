package com.honchipay.honchi_android.chat.viewModel;

import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.chat.model.ChatRepository;
import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class ChatViewModel extends BaseViewModel {
    private final String TAG = ChatViewModel.class.getSimpleName();
    private final ChatRepository repository = new ChatRepository();
    private String roomId;
    public final ObservableField<String> roomTitle = new ObservableField<>();
    public MutableLiveData<List<ChatRoomItem>> chatRoomListLiveData = new MutableLiveData<>();

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setRoomTitleValidation(String preRoomTitle) {
        roomTitle.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (!roomTitle.get().equals("") && !roomTitle.get().equals(preRoomTitle)) {
                    changeChatRoomTitle();
                }
            }
        });
    }

    public void getParticipatingChatRooms() {
        DisposableSingleObserver<Response<List<ChatRoomItem>>> chatRoomObserver = new CustomDisposableSingleObserver<Response<List<ChatRoomItem>>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<List<ChatRoomItem>> listResponse) {
                if (listResponse.isSuccessful() && listResponse.code() == 200) {
                    chatRoomListLiveData.postValue(listResponse.body());
                }
            }
        };

        addDisposable(repository.getChatRooms(chatRoomObserver));
    }

    private void changeChatRoomTitle() {
        DisposableSingleObserver<Response<Void>> roomTitleObserver = new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<Void> listResponse) {
            }
        };

        addDisposable(repository.changeRoomTitle(roomId, roomTitle.get(), roomTitleObserver));
    }
}