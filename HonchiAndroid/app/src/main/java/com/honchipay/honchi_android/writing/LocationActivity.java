package com.honchipay.honchi_android.writing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.honchipay.honchi_android.R;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    int latitude;
    int longitude;
    Button sendDataBtn;
    double getLatitude;
    double getLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        sendDataBtn = findViewById(R.id.location_button);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sendDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("위도",latitude);
                intent.putExtra("경도",longitude);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e("LocationActivity","onMapReady");

        mMap = googleMap;

        //goToLocationZoom();

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions mOptions = new MarkerOptions();
                mOptions.title("위치 설정");
                latitude = (int) latLng.latitude;
                longitude = (int) latLng.longitude;
                mOptions.snippet(latitude + ", " + longitude);
                mOptions.position(new LatLng(latitude, longitude));
                mMap.addMarker(mOptions);

                Log.e("locationActivity", "위도: "+latitude);
                Log.e("locationActivity","경도: "+longitude);
            }
        });
    }

    public void goToLocationZoom(){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try{
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null){
                getLatitude = location.getLatitude();
                getLongitude = location.getLongitude();
            }

        }catch (SecurityException e){
            e.printStackTrace();
        }

        LatLng latLng = new LatLng(getLatitude, getLongitude);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        mMap.moveCamera(update);
    }

}