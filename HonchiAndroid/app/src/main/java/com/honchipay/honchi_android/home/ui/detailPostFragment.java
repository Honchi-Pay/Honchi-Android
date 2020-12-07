package com.honchipay.honchi_android.home.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentDetailPostBinding;
import com.honchipay.honchi_android.home.data.detailPost;
import com.honchipay.honchi_android.home.ViewModel.homeViewModel;

public class detailPostFragment extends Fragment {
    private FragmentDetailPostBinding binding;
    private final homeViewModel viewModel = new homeViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_post,container,false);
        View root = binding.getRoot();

        int postId = getArguments().getInt("position");

        viewModel.detailPost(postId);

        viewModel.getDetailLiveData.observe(getViewLifecycleOwner(), new Observer<detailPost>() {
            @Override
            public void onChanged(detailPost detailPost) {
                binding.detailDateTextView.setText(detailPost.getCreatedAt());
                binding.detailContentTextView.setText(detailPost.getContent());
                Glide.with(getContext()).load(detailPost.getImages()).into(binding.detailImageImageView);
                binding.detailPeopleTextView.setText(detailPost.getWriter());
                binding.detailTitleTextView.setText(detailPost.getTitle());
                binding.detailLocationTextView.setText(detailPost.getAddress());
                if(detailPost.getMine() == true){
                    binding.detailModifyButton.getVisibility();
                    binding.detailAttendButton.getVisibility();
                }
                if(detailPost.getAttend() == true){
                    binding.detailAttendButton.setText(getString(R.string.buy_delete));
                } else {
                    binding.detailAttendButton.setText(getString(R.string.buy_together));
                }

            }
        });

        binding.detailAttendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"성공적으로 메신저가 생성됬습니다.",Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.detailMessageButton.getVisibility();
        binding.detailMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getContext(),);
                startActivity(intent);*/
            }
        });
    }
}