package com.example.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Insert extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final Button insert = findViewById(R.id.insert);

        final DBHelper DB = new DBHelper(this);

        insert.setOnClickListener(view -> {
            final EditText eCode = findViewById(R.id.eCode);
            final EditText eName = findViewById(R.id.eName);
            final EditText eDept = findViewById(R.id.eDept);
            final RadioGroup eGender = findViewById(R.id.eGender);
            final RadioButton eGen = findViewById(eGender.getCheckedRadioButtonId());
            final EditText eSalary = findViewById(R.id.eSalary);

            String code = eCode.getText().toString();
            String name = eName.getText().toString();
            String dept = eDept.getText().toString();
            String gender = eGen.getText().toString();

            String salary = eSalary.getText().toString();

            boolean check = DB.insertEmployee(code, name, gender, dept, Long.parseLong(salary));
            if(check)
                Toast.makeText(Insert.this, "Insert Successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Insert.this, "Insert Failed", Toast.LENGTH_SHORT).show();
        });


    }
}