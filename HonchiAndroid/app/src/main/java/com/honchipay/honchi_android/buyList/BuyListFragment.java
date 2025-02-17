package com.honchipay.honchi_android.buyList;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentBuyListBinding;

import java.util.List;

public class BuyListFragment extends Fragment {
    private FragmentBuyListBinding binding;
    BuyListViewModel viewModel = new BuyListViewModel();
    private BuyListAdapter adapter = new BuyListAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buy_list, container, false);

        initRecyclerView();
        getBuyList();

        return binding.getRoot();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        BuyListAdapter adapter = new BuyListAdapter();

        binding.buyListRecyclerView.setHasFixedSize(true);
        binding.buyListRecyclerView.setLayoutManager(linearLayoutManager);

        binding.buyListRecyclerView.setAdapter(adapter);
    }

    private void getBuyList(){
        viewModel.getBuyList();

        viewModel.buyListLiveData.observe(getViewLifecycleOwner(), new Observer<List<BuyListResponse>>() {
            @Override
            public void onChanged(List<BuyListResponse> buyList) {
                adapter.notifyDataChanged(buyList);
            }
        });
    }

}