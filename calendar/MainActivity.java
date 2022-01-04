package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendar =  findViewById(R.id.calendar);
        TextView date_view =  findViewById(R.id.date_view);

        calendar.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            String Date = i2 + "-" + (i1 + 1) + "-" + i;
            date_view.setText(Date);
        });
    }
}