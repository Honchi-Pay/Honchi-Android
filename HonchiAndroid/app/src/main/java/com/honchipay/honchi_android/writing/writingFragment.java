package com.honchipay.honchi_android.writing;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentWritingBinding;

public class writingFragment extends Fragment {
    private FragmentWritingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_writing, container, false);

        return inflater.inflate(R.layout.fragment_writing, container, false);
    }

    void setInit(){
        binding.writingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }
}