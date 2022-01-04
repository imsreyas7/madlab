package com.example.smsgps;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText contact_no,user_message;
    Button send;
    Button click;
    LocationManager locMg;
    LocationListener lis;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click = (Button) findViewById(R.id.gps);
        contact_no = findViewById(R.id.contact_number);
        user_message = findViewById(R.id.user_message);
        send = findViewById(R.id.sendButton);
        locMg = (LocationManager) getSystemService(LOCATION_SERVICE);

        lis = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String loc = "Latitude = " + location.getLatitude() + "\nLongitude = " + location.getLongitude();
                user_message.setText(loc);
            }
        };
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        click.setOnClickListener(view -> {
            locMg.requestLocationUpdates("gps", 5000, 0, lis);
        });

        ActivityCompat.requestPermissions(MainActivity.this,new
                String[]{READ_SMS,RECEIVE_SMS,SEND_SMS,READ_PHONE_STATE},1);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg(contact_no.getText().toString(), user_message.getText().toString());
            }
        });

    }

    void sendMsg(String number, String message)
    {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, message, null, null);
    }

}