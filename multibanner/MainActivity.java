package com.example.multy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView banner,  counter_text;

    private Button counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banner = (TextView) findViewById(R.id.banner);

        counter_text = (TextView) findViewById(R.id.counter);
        counter = (Button) findViewById(R.id.bt_counter);

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(counter_text.getText().toString());
                current++;
                counter_text.setText(String.valueOf(current));
            }});

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> {
                    Random random = new Random();
                    int clr1 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                    int clr2 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                    banner.setBackgroundColor(clr1);
                    banner.setTextColor(clr2);
//                    int[] location =  new int[2];
//                    banner.getLocationOnScreen(location);
                });
            }
        }).start();

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> banner.animate().translationXBy(5));
            }
        }).start();

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> banner.animate().translationYBy(5));
            }
        }).start();
    }
}