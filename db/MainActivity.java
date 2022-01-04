package com.example.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button create_button = findViewById(R.id.create_button);
        Button insert_button = findViewById(R.id.insert_button);
        Button update_button = findViewById(R.id.update_button);
        Button delete_button = findViewById(R.id.delete_button);
        Button retrieve_button = findViewById(R.id.retrieve_button);


        create_button.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "Database Created", Toast.LENGTH_SHORT).show());

        insert_button.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Insert.class)));

        update_button.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Update.class)));

        delete_button.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Delete.class)));

        retrieve_button.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Retrieve.class)));


    }
}