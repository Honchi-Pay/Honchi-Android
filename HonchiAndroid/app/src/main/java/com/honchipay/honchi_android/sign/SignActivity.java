package com.honchipay.honchi_android.sign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.sign.Fragment.LoginFragment;
import com.honchipay.honchi_android.sign.Fragment.SignUp_email_Fragment;

public class SignActivity extends AppCompatActivity {
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        String division = getIntent().getStringExtra("splash");
        transaction = getSupportFragmentManager().beginTransaction();

        switch (division) {
            case "login":
                transaction.add(R.id.sign_fragment, new LoginFragment()).commit();
                break;
            case "signUp":
                transaction.add(R.id.sign_fragment, new SignUp_email_Fragment()).commit();
                break;
            default:
                Toast.makeText(this,"올바르지 않은 요청입니다.",Toast.LENGTH_LONG).show();
        }
    }

    void replaceFragment(Fragment fragment) {
        transaction.replace(R.id.sign_fragment, fragment).commit();
    }
}