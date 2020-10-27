package com.honchipay.honchi_android.Sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.honchipay.honchi_android.R;

public class SignActivity extends AppCompatActivity {
    Intent intent;
    String division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        intent = getIntent();
        division = intent.getExtras().getString("splash");

        if(division == "login"){

        } else if(division == "signUp"){

        } else{
            Toast.makeText(this,"올바르지 않은 요청입니다.",Toast.LENGTH_LONG).show();
        }

    }
}