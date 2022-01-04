package com.example.shapes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Called when the user taps the Send button */
    public void shapes(View view) {
        Intent intent = new Intent(this, ShapeActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Send button */
    public void animate(View view) {
        Intent intent = new Intent(this, CarActivity.class);
        startActivity(intent);
    }
}