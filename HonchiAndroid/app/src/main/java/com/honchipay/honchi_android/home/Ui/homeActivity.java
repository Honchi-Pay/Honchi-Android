package com.honchipay.honchi_android.home.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class homeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private homeAdapter adapter;
    private ArrayList foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

    }


}