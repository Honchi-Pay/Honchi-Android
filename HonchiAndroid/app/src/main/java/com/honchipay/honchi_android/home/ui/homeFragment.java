package com.honchipay.honchi_android.home.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentHomeBinding;
import com.honchipay.honchi_android.home.data.newPost;
import com.honchipay.honchi_android.home.ViewModel.homeViewModel;

import java.util.List;

public class homeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private postAdapter newPostAdapter;
    private final homeViewModel viewModel = new homeViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        View root  = binding.getRoot();

        binding.homeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = (String) parent.getItemAtPosition(position);
                if(category.equals("배달")){
                    getNewPostView("FOOD");
                } else {
                    getNewPostView("PRODUCT");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}});

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        newPostAdapter = new postAdapter((homeActivity)getActivity());

        setInit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setOnClick();

    }

    private void setInit(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);

        binding.homeNewPostRecyclerview.setLayoutManager(linearLayoutManager);
        binding.homeNewPostRecyclerview.setHasFixedSize(true);

        binding.homeNewPostRecyclerview.setAdapter(newPostAdapter);

        setSpinner();
        getNewPostView("FOOD");
    }

    private void setSpinner(){
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.category, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.homeSpinner.setAdapter(adapter);
    }

    private void getNewPostView(String category){
        viewModel.getNewPost(category);

        viewModel.newPostLiveData.observe(getViewLifecycleOwner(), new Observer<List<newPost>>() {
            @Override
            public void onChanged(List<newPost> newPostList) {
                newPostAdapter.notifyDataChanged(newPostList);
            }
        });
    }

    public void setOnClick(){
        binding.homeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.homeSearchEditText.getText().toString().length() == 0){
                    Toast.makeText(getContext(),"검색할 내용을 입력해주세요",Toast.LENGTH_LONG).show();
                } else{
                    Fragment fragment = new SearchFragment();
                    Bundle result = new Bundle();
                    result.putString("search",binding.homeSearchEditText.getText().toString());
                    fragment.setArguments(result);

                    homeActivity activity = (homeActivity) getActivity();
                    activity.onFragmentChanged("search");
                }
            }
        });

        binding.homeChickenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PostByCategoryFragment();
                Bundle result = new Bundle();
                result.putString("category","FOOD");
                result.putString("item","CHICKEN");
                fragment.setArguments(result);

                homeActivity activity = (homeActivity) getActivity();
                activity.onFragmentChanged(fragment);

                Log.e("homeFragment","button click");
            }
        });



        binding.homeItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PostByCategoryFragment();
                Bundle result = new Bundle();
                result.putString("category","PRODUCT");
                result.putString("item","IT_DIGITAL");
                fragment.setArguments(result);

                homeActivity activity = (homeActivity) getActivity();
                activity.onFragmentChanged(fragment);

                Log.e("homeFragment","button click");
            }
        });

    }
}