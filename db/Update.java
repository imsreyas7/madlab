package com.example.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final Button update = findViewById(R.id.update);

        final DBHelper DB = new DBHelper(this);

        update.setOnClickListener(view -> {
            final EditText eCode = findViewById(R.id.eCodeUpdate);
            final EditText eName = findViewById(R.id.eNameUpdate);
            final EditText eDept = findViewById(R.id.eDeptUpdate);
            final RadioGroup eGender = findViewById(R.id.eGenderUpdate);
            final RadioButton eGen = findViewById(eGender.getCheckedRadioButtonId());
            final EditText eSalary = findViewById(R.id.eSalaryUpdate);

            String code = eCode.getText().toString();
            String name = eName.getText().toString();
            String dept = eDept.getText().toString();
            String gender = eGen.getText().toString();
            String salary = eSalary.getText().toString();

            boolean check = DB.updateEmployee(code, name, gender, dept, Long.parseLong(salary));
            if(check)
                Toast.makeText(Update.this, "Update Successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Update.this, "Update Failed", Toast.LENGTH_SHORT).show();
        });
    }
}