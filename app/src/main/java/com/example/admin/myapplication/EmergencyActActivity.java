package com.example.admin.myapplication;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;


public class EmergencyActActivity extends AppCompatActivity
{

    private final static int ALL_PERMISSIONS_RESULT = 101;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    final String TAG = "GPS";
    LocationManager locationManager;
    Location loc;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    boolean isGPS = false;
    boolean isNetwork = false;
    boolean canGetLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergengyact);


        FrameLayout stranger = (FrameLayout) (findViewById(R.id.stranger));
        stranger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EmergencyActActivity.this, EmargencyResponseActivity.class);
                startActivity(intent);
            }
        });

        FrameLayout self = (FrameLayout) (findViewById(R.id.self));

        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(EmergencyActActivity.this,LocActivity.class);
                intent.putExtra("number", getIntent().getStringExtra("number"));
                startActivity(intent);


            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent= new Intent(EmergencyActActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
