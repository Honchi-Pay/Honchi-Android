package com.honchipay.honchi_android.sign.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentSignUpUserInfoBinding;
import com.honchipay.honchi_android.sign.Data.Gender;
import com.honchipay.honchi_android.sign.Data.SignUpProcess;
import com.honchipay.honchi_android.sign.Data.SignUpRequest;
import com.honchipay.honchi_android.sign.ViewModel.SignUpViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import io.reactivex.rxjava3.core.Observer;
import retrofit2.Response;

public class SignUpUserInfoFragment extends Fragment implements LocationListener {
    FragmentSignUpUserInfoBinding binding;
    SignUpViewModel signUpViewModel;
    SignUpRequest signUpRequest;
    String inputPhoneNumber = null;
    String inputNickName = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpRequest.setEmail(requireArguments().getString("email"));
        signUpRequest.setPassword(requireArguments().getString("password"));
        startLocationService();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_user_info, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.SignUpInfoGenderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.SignUpInfo_male_radioButton) {
                signUpRequest.setSex(Gender.MALE);
            } else if (checkedId == R.id.SignUpInfo_female_radioButton) {
                signUpRequest.setSex(Gender.FEMALE);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.SignUpInfoSignUpButton.setOnClickListener(v -> {
            inputPhoneNumber = binding.SignUpInfoPhoneEditText.getText().toString();
            inputNickName = binding.SignUpInfoNameEditText.getText().toString();

            if (inputPhoneNumber != null) {
                signUpRequest.setNickName(inputNickName);
                signUpRequest.setPhoneNumber(inputPhoneNumber);
                signUpViewModel.signUp(signUpRequest);
            }
        });

        signUpViewModel.haveToNextPageLiveData.observe(getViewLifecycleOwner(), signUpProcess -> {
            if (signUpProcess == SignUpProcess.SIGN_UP) {
                Intent intent = new Intent(getContext(), );
                requireActivity().startActivity(intent);
            }
        });

        binding.SignUpInfoBackButton.setOnClickListener(v -> requireActivity().finish());
    }

    private void startLocationService() {
        LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        signUpRequest.setLat(location.getLatitude());
        signUpRequest.setLon(location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }
}