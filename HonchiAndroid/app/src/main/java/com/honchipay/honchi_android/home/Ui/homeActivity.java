package com.honchipay.honchi_android.home.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.chat.view.ChatListFragment;
import com.honchipay.honchi_android.databinding.ActivityHomeBinding;
import com.honchipay.honchi_android.profile.view.ProfileFragment;
import com.honchipay.honchi_android.writing.writingFragment;

public class homeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        transaction = fm.beginTransaction();
        transaction.replace(R.id.home_fragment, new homeFragment());
        transaction.commit();

        setSupportActionBar(binding.homeToolbar);

        binding.homeNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navi_home: {
                        onFragmentChanged("home");
                        break;
                    }

                    case R.id.navi_buylist: {
                        break;
                    }

                    case R.id.navi_message: {
                        onFragmentChanged("message");
                        break;
                    }

                    case R.id.navi_profile: {
                        onFragmentChanged("profile");
                        break;
                    }
                }
                return true;
            }
        });

    }

    public void onFragmentChanged(Fragment fragment){
        transaction = fm.beginTransaction();
        transaction.replace(R.id.home_fragment, fragment).commit();
    }
    public void onFragmentChanged(String s) {
        transaction = fm.beginTransaction();

        switch (s) {
            case "search": {
                transaction.replace(R.id.home_fragment, new SearchFragment()).commit();
                break;
            }
            case "home": {
                transaction.replace(R.id.home_fragment, new homeFragment()).commit();
                break;
            }
            case "buyList": {

                break;
            }
            case "message": {
                transaction.replace(R.id.home_fragment, new ChatListFragment()).commit();
                break;
            }
            case "writing": {
                transaction.replace(R.id.home_fragment, new writingFragment()).commit();
                break;
            }
            case "postByCategory":{
                //transaction.replace(R.id.home_fragment,new PostByCategoryFragment()).commit();
            }
            case "profile": {
                transaction = fm.beginTransaction();
                transaction.replace(R.id.home_fragment, new ProfileFragment()).commit();
                break;
            }
            case "detailItem": {
                transaction.replace(R.id.home_fragment, new detailPostFragment()).commit();
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeBar_back_button:{
                onFragmentChanged("writing");
            }
        }
        return true;
    }


}