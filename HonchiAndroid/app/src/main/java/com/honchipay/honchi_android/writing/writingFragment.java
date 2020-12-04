package com.honchipay.honchi_android.writing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentWritingBinding;
import com.honchipay.honchi_android.home.Data.homeItem;
import com.honchipay.honchi_android.home.Ui.homeAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static java.sql.DriverManager.println;

public class writingFragment extends Fragment {
    private FragmentWritingBinding binding;
    private homeAdapter adapter;
    String setCategory;
    File imageFile;
    int latitude;
    int longitude;
    private writingViewModel viewModel = new writingViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_writing, container, false);

        setInit();

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.writingLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("writingFragment","location click");
                Intent intent = new Intent(getContext(), LocationActivity.class);
                startActivityForResult(intent, 200);
            }
        });

        binding.writingDeliveryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setFoodView();
                setCategory = "FOOD";
            }
        });

        binding.writingGoodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProductView();
                setCategory = "PRODUCT";
            }
        });

        binding.writingPhotoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("writingFragment","photo click");
                getPhoto();
            }
        });

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writing();
            }
        });

    }

    void setInit() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.writingRecyclerView.setLayoutManager(linearLayoutManager);
        binding.writingRecyclerView.setHasFixedSize(true);

        imageFile = new File(Environment.getExternalStorageDirectory(), "honchi.png");
    }

    void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, 300);
    }


    private void writing() {
        String getTitle = binding.writingTitleEditText.toString();
        String getContent = binding.writingContentEditText.toString();
        HashMap hashMap = new HashMap<String, RequestBody>();

        RequestBody requestimage = RequestBody.create(MediaType.parse("image"),imageFile);

        MultipartBody.Part title = MultipartBody.Part.createFormData(
                "title",
                getTitle
        );

        MultipartBody.Part content = MultipartBody.Part.createFormData(
                "content",
                getContent
        );

        MultipartBody.Part category = MultipartBody.Part.createFormData(
                "category",
                setCategory
        );

        MultipartBody.Part item = MultipartBody.Part.createFormData(
                "item",
                "CHICKEN"
        );

        MultipartBody.Part lat = MultipartBody.Part.createFormData(
                "lat",
                String.valueOf(latitude)
        );

        MultipartBody.Part lon = MultipartBody.Part.createFormData(
                "lon",
                String.valueOf(longitude)
        );

        MultipartBody.Part image = MultipartBody.Part.createFormData(
                "images",
                String.valueOf(requestimage)
        );

        hashMap.put("title",title);
        hashMap.put("content",content);
        hashMap.put("category",category);
        hashMap.put("item",item);
        hashMap.put("images",image);
        hashMap.put("lat",lat);
        hashMap.put("lon",lon);

        Log.e("writingActivity",hashMap.toString());

        viewModel.writing(hashMap);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (resultCode) {
                case 200: {
                    latitude = data.getIntExtra("위도", 0);
                    longitude = data.getIntExtra("경도", 0);

                    Log.e("writingFragment", String.valueOf(latitude + longitude));
                }

                case 300: {
                    try {
                        Uri uri = data.getData();
                        imageFile.delete();
                        imageFile.createNewFile();

                        FileOutputStream out = new FileOutputStream(imageFile);
                        getActivity().getContentResolver().openInputStream(uri);
                        out.close();

                    } catch (Exception e) {
                        Log.e("writingFragment", e.getMessage());
                    }
                }
            }
        }
    }

    private void setFoodView() {
        adapter = new homeAdapter();

        adapter.addItem(new homeItem("치킨", R.drawable.home_chinesefood));
        adapter.addItem(new homeItem("피자/양식", R.drawable.home_pizza));
        adapter.addItem(new homeItem("중국집", R.drawable.home_chinesefood));
        adapter.addItem(new homeItem("족발/보쌈", R.drawable.home_jokbal));
        adapter.addItem(new homeItem("일식/돈가스", R.drawable.home_japanesefood));
        adapter.addItem(new homeItem("분식", R.drawable.home_boonsik));
        adapter.addItem(new homeItem("한식", R.drawable.home_koreanfood));
        adapter.addItem(new homeItem("야식", R.drawable.home_yasik));
        adapter.addItem(new homeItem("카페/디저트", R.drawable.home_cafe));

        binding.writingRecyclerView.setAdapter(adapter);
    }

    private void setProductView() {
        adapter = new homeAdapter();

        adapter.addItem(new homeItem("패션의류", R.drawable.home_fashion));
        adapter.addItem(new homeItem("잡화/뷰티", R.drawable.home_beauty));
        adapter.addItem(new homeItem("IT/디지털", R.drawable.home_it));
        adapter.addItem(new homeItem("스포츠/건강", R.drawable.home_sports));
        adapter.addItem(new homeItem("취미", R.drawable.home_hobby));
        adapter.addItem(new homeItem("책/티켓", R.drawable.home_book));
        adapter.addItem(new homeItem("데코/문구", R.drawable.home_deco));
        adapter.addItem(new homeItem("식료품/생필품", R.drawable.home_food));

        binding.writingRecyclerView.setAdapter(adapter);
    }
}