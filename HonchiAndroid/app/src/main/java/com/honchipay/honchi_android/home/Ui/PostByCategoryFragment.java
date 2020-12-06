package com.honchipay.honchi_android.home.Ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.home.Data.getPost;
import com.honchipay.honchi_android.home.ViewModel.homeViewModel;

import java.util.List;

public class PostByCategoryFragment extends Fragment {
    private final homeViewModel viewModel = new homeViewModel();
    postByCategoryAdapter postByCategoryAdapter = new postByCategoryAdapter();
    String category;
    String item;
    postByCategoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(getArguments() != null){
            category = getArguments().getString("category");
            item = getArguments().getString("item");

            Log.e("postbycategory",category + item);
        }

        setInit();

        return inflater.inflate(R.layout.fragment_post_by_category, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView;
        recyclerView = getActivity().findViewById(R.id.test_post_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        adapter = new postByCategoryAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

    }

    public void setInit(){
        viewModel.getPostList(category,item, 10);

        viewModel.getPostLiveData.observe(getViewLifecycleOwner(), new Observer<List<getPost>>() {
            @Override
            public void onChanged(List<getPost> getPosts) {
                adapter.items.addAll(getPosts);
                adapter.notifyDataSetChanged();
            }
        });
    }

}