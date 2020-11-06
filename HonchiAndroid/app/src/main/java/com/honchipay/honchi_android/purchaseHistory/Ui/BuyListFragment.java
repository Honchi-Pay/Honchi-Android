package com.honchipay.honchi_android.purchaseHistory.Ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;

public class BuyListFragment extends Fragment {
    ViewDataBinding binding;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buylist_history, container, false);

        return binding.getRoot();
    }

}

