package com.honchipay.honchi_android.home.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentSearchBinding;
import com.honchipay.honchi_android.home.ViewModel.homeViewModel;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private final homeViewModel viewModel = new homeViewModel();
    private String search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false);
        View root = binding.getRoot();

        if(getArguments() != null){
            search = getArguments().getString("search");
        }

        setInit(search);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void setInit(String search){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.categoryPostRecyclerview.setLayoutManager(linearLayoutManager);
        binding.categoryPostRecyclerview.setHasFixedSize(true);

        viewModel.searchPost(search,5);

    }
}