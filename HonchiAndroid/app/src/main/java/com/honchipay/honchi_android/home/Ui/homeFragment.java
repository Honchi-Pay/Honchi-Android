package com.honchipay.honchi_android.home.Ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentHomeBinding;
import com.honchipay.honchi_android.home.Data.homeItem;
import com.honchipay.honchi_android.home.ViewModel.homeViewModel;

public class homeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private homeAdapter adapter;
    private homeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        View root  = binding.getRoot();

        setInit();


        return root;
    }

    private void setInit(){
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),5);

        binding.homeFoodRecyclerview.setLayoutManager(layoutManager);
        binding.homeFoodRecyclerview.setHasFixedSize(true);

        binding.homeProductRecyclerview.setLayoutManager(layoutManager);
        binding.homeProductRecyclerview.setHasFixedSize(true);

        binding.homeNewPostRecyclerview.setLayoutManager(layoutManager);
        binding.homeNewPostRecyclerview.setHasFixedSize(true);

        setFoodView();
        setProductView();
        setNewPostView();
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
    }

    private void setProductView() {
        adapter = new homeAdapter();

        adapter.addItem(new homeItem("패션의류", R.drawable.home_fashion));
        adapter.addItem(new homeItem("잡화/뷰티",R.drawable.home_beauty));
        adapter.addItem(new homeItem("IT/디지털",R.drawable.home_it));
        adapter.addItem(new homeItem("스포츠/건강",R.drawable.home_sports));
        adapter.addItem(new homeItem("취미",R.drawable.home_hobby));
        adapter.addItem(new homeItem("책/티켓",R.drawable.home_book));
        adapter.addItem(new homeItem("데코/문구",R.drawable.home_deco));
        adapter.addItem(new homeItem("식료품/생필품",R.drawable.home_food));
    }

    private void setNewPostView() {

    }
}
