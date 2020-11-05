package com.honchipay.honchi_android.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.honchipay.honchi_android.R;

public class EditPrivateInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_private_info);

        Intent intent =  getIntent();
        String division = intent.getStringExtra("editActivity");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (division) {
            case "profile":
                String toEditValue = intent.getStringExtra("userProfileItem");
                EditProfileFragment profileFragment = new EditProfileFragment();

                Bundle bundle = new Bundle();
                bundle.putString("userInformation", toEditValue);

                profileFragment.setArguments(bundle);
                transaction.add(R.id.change_info_frameLayout, profileFragment).commit();
                break;
            case "password":
                transaction.add(R.id.change_info_frameLayout, new EditPasswordFragment()).commit();
                break;
            default:
                Toast.makeText(this, "올바르지 않은 요청입니다.", Toast.LENGTH_LONG).show();
                finish();
        }
    }
}