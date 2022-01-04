package com.example.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        final EditText eCode = findViewById(R.id.eCodeDelete);

        final Button delete = findViewById(R.id.retrieve);

        final DBHelper DB = new DBHelper(this);

        delete.setOnClickListener(view -> {
            String code = eCode.getText().toString();

            boolean check = DB.deleteEmployee(code);
            if(check)
                Toast.makeText(Delete.this, "Delete Successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Delete.this, "Delete Failed", Toast.LENGTH_SHORT).show();
        });
    }
}