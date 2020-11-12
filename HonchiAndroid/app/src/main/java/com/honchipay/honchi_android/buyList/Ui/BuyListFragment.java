package com.honchipay.honchi_android.buyList.Ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.buyList.ViewModel.BuyListViewModel;
import com.honchipay.honchi_android.databinding.FragmentBuylistBinding;

public class BuyListFragment extends Fragment {
    private FragmentBuylistBinding binding;
    BuyListViewModel buyListViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buylist, container, false);

        return binding.getRoot();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        BuyListAdapter adapter = new BuyListAdapter();
        binding.buyListRecyclerView.setAdapter(adapter);
    }

}