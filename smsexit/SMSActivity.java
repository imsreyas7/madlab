package com.example.labpractice;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labpractice.R;

import java.util.HashMap;
import java.util.Map;

public class SMSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button sendButton = findViewById(R.id.sendButton);
        final EditText phoneText = findViewById(R.id.contact_number);
        final EditText smsText = findViewById(R.id.user_message);
        Map<String, Integer> messageCounts = new HashMap<>();

        ActivityCompat.requestPermissions(SMSActivity.this, new String[]{
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS
        }, 1);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneText.getText().toString();
                String smsMessage = smsText.getText().toString();

                if(messageCounts.getOrDefault(phoneNumber, 0) >= 2){
                    Toast.makeText(getApplicationContext(), "You have messaged " + phoneNumber + " more than twice! Exiting...", Toast.LENGTH_SHORT).show();
                    finishAffinity();
                }

                messageCounts.put(phoneNumber, messageCounts.getOrDefault(phoneNumber, 0) + 1);

                PendingIntent sentIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("Sent"), 0);
                PendingIntent deliveredIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("Delivered"), 0);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, smsMessage, sentIntent, deliveredIntent);
                //Can null the sentIntent and deliveredIntent arguments as well

                Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}