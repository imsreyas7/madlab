package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {

    TextView workingsTV;
    TextView resultsTV;
    String workings = "";
    int brackets_flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews() {
        workingsTV = findViewById(R.id.workingText);
        resultsTV = findViewById(R.id.resultText);
    }

    private void setWorkings(String givenValue) {
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }

    public void equalsOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        try {
            result = (double) engine.eval(workings);
        } catch (ScriptException e) {
            Toast.makeText(this, "Please Enter A valid input", Toast.LENGTH_SHORT).show();
        }

        if(result != null) {
            resultsTV.setText(String.valueOf(result.doubleValue()));
            workingsTV.setText(String.valueOf(result.doubleValue()));
            workings = String.valueOf(result.doubleValue());
        }
    }

    public void clearOnClick(View view) {
        resultsTV.setText("");
        workingsTV.setText("");
        workings = "";
    }

    public void backOnClick(View view) {
        if (workings != null && workings.length() > 0) {
            workingsTV.setText(workings.substring(0, workings.length() - 1));
            workings = workings.substring(0, workings.length() - 1);
        }
    }

    public void bracketsOnClick(View view) {
        if(brackets_flag == 1) setWorkings("(");
        else setWorkings(")");
        brackets_flag *= -1;
    }

    public void divideOnClick(View view) {
        setWorkings("/");
    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void multiplyOnClick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void subtractOnClick(View view) {
        setWorkings("-");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void addOnClick(View view) {
        setWorkings("+");
    }

    public void decimalOnClick(View view) {
        setWorkings(".");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }
}