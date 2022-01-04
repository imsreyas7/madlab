package com.example.shapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class CarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Button bt_animate=findViewById(R.id.bt_animate);
        Button bt_rotate=findViewById(R.id.bt_rotate);
        Button bt_up = findViewById(R.id.bt_up);
        Button bt_down = findViewById(R.id.bt_down);
        Button bt_forward=findViewById(R.id.bt_forward);
        Button bt_backward=findViewById(R.id.bt_backward);
        final ImageView iv_animate=findViewById(R.id.iv_animate);
        bt_animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in));
            }
        });

        bt_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_in));
            }
        });

        bt_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationXBy(-300f).setDuration(600);
            }
        });
        bt_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationXBy(+300f).setDuration(600);
            }
        });
        bt_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationYBy(-300f).setDuration(600);
            }
        });
        bt_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationYBy(+300f).setDuration(600);
            }
        });
    }

}