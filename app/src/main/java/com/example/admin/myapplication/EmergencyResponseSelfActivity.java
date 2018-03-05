package com.example.admin.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by admin on 11/8/2017.
 */

public class EmergencyResponseSelfActivity extends AppCompatActivity {
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_response);




        timer= new CountDownTimer(5000, 1000) {
            public void onFinish() {
                String phone = "108";
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
                if (ActivityCompat.checkSelfPermission(EmergencyResponseSelfActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyResponseSelfActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(intent);

                finish();

            }
            public void onTick ( long millisUntilFinished){
            }

        }.start();


        TextView ambulance = (TextView) (findViewById(R.id.call_ambulance));
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "102";
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
                if (ActivityCompat.checkSelfPermission(EmergencyResponseSelfActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyResponseSelfActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(intent);
            }
        });

        TextView hospital = (TextView) (findViewById(R.id.call_hospital));

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "108";
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
                if (ActivityCompat.checkSelfPermission(EmergencyResponseSelfActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyResponseSelfActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(intent);
            }
        });

        TextView police = (TextView) (findViewById(R.id.call_police));

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "100";
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
                if (ActivityCompat.checkSelfPermission(EmergencyResponseSelfActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyResponseSelfActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(intent);
            }
        });


    }
    @Override
    public void onPause(){
        super.onPause();
        if (timer!=null) {
            timer.cancel();
        }
    }

}
