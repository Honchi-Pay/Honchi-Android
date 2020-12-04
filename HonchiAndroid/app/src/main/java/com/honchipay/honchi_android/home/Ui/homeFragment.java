package com.honchipay.honchi_android.home.Ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentHomeBinding;
import com.honchipay.honchi_android.home.Data.homeItem;
import com.honchipay.honchi_android.home.ViewModel.homeViewModel;

import static java.sql.DriverManager.println;

public class homeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private homeAdapter foodAdapter;
    private homeAdapter goodsAdapter;
    private homeViewModel viewModel = new homeViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        View root  = binding.getRoot();

        setInit();

        binding.homeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = (String) parent.getItemAtPosition(position);
                getNewPostView(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    private void setInit(){
        GridLayoutManager foodLayoutManager = new GridLayoutManager(getContext(),5);
        GridLayoutManager goodsLayoutManager = new GridLayoutManager(getContext(),4);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);

        binding.homeFoodRecyclerview.setLayoutManager(foodLayoutManager);
        binding.homeFoodRecyclerview.setHasFixedSize(true);

        binding.homeProductRecyclerview.setLayoutManager(goodsLayoutManager);
        binding.homeProductRecyclerview.setHasFixedSize(true);

        binding.homeNewPostRecyclerview.setLayoutManager(linearLayoutManager);
        binding.homeNewPostRecyclerview.setHasFixedSize(true);

        setSpinner();
        setFoodView();
        setProductView();
        getNewPostView("FOOD");
    }

    private void setSpinner(){
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.category, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.homeSpinner.setAdapter(adapter);
    }

    private void getNewPostView(String category){
        viewModel.getNewPost(category);
    }

    private void setFoodView() {
        foodAdapter = new homeAdapter();

        foodAdapter.addItem(new homeItem("치킨", R.drawable.home_chinesefood));
        foodAdapter.addItem(new homeItem("피자/양식", R.drawable.home_pizza));
        foodAdapter.addItem(new homeItem("중국집", R.drawable.home_chinesefood));
        foodAdapter.addItem(new homeItem("족발/보쌈", R.drawable.home_jokbal));
        foodAdapter.addItem(new homeItem("일식/돈가스", R.drawable.home_japanesefood));
        foodAdapter.addItem(new homeItem("분식", R.drawable.home_boonsik));
        foodAdapter.addItem(new homeItem("한식", R.drawable.home_koreanfood));
        foodAdapter.addItem(new homeItem("야식", R.drawable.home_yasik));
        foodAdapter.addItem(new homeItem("카페/디저트", R.drawable.home_cafe));

        binding.homeFoodRecyclerview.setAdapter(foodAdapter);
    }

    private void setProductView() {
        goodsAdapter = new homeAdapter();

        goodsAdapter.addItem(new homeItem("패션의류", R.drawable.home_fashion));
        goodsAdapter.addItem(new homeItem("잡화/뷰티",R.drawable.home_beauty));
        goodsAdapter.addItem(new homeItem("IT/디지털",R.drawable.home_it));
        goodsAdapter.addItem(new homeItem("스포츠/건강",R.drawable.home_sports));
        goodsAdapter.addItem(new homeItem("취미",R.drawable.home_hobby));
        goodsAdapter.addItem(new homeItem("책/티켓",R.drawable.home_book));
        goodsAdapter.addItem(new homeItem("데코/문구",R.drawable.home_deco));
        goodsAdapter.addItem(new homeItem("식료품/생필품",R.drawable.home_food));

        binding.homeProductRecyclerview.setAdapter(goodsAdapter);
    }

}
