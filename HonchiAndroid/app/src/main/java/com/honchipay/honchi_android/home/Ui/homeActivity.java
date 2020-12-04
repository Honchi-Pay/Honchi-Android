package com.honchipay.honchi_android.home.Ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.ActivityHomeBinding;
import com.honchipay.honchi_android.writing.writingFragment;

public class homeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        transaction = fm.beginTransaction();
        transaction.replace(R.id.home_fragment, new writingFragment());
        transaction.commit();

        binding.homeNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navi_home: {
                        transaction.replace(R.id.home_fragment, new homeFragment()).commit();
                        break;
                    }

                    case R.id.navi_buylist: {
                        break;
                    }

                    case R.id.navi_message: {
                        break;
                    }

                    case R.id.navi_profile: {
                        break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_home, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case R.id.homeBar_back_button
        }
    }*/
}