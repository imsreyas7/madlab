package com.example.gpssdcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button read, write;
    TextView ReadBox;
    EditText WriteBox;
    String fileName, filePath, fileContents;

    TextView lat;
    TextView lon;
    Button click;
    LocationManager locMg;
    LocationListener lis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        read = findViewById(R.id.Read);
        write = findViewById(R.id.Write);
        ReadBox = findViewById(R.id.ReadBox);
        WriteBox = findViewById(R.id.WriteBox);

        fileName = "myFile.txt";
        filePath = "FileDir";
        if (!isExternalStorageAvailableRW()) {
            write.setEnabled(false);
        }
        write.setOnClickListener(view -> {
            ReadBox.setText("");
            fileContents = WriteBox.getText().toString().trim();
            if (!fileContents.equals("")) {
                //Get external files dir
                File myExtFile = new File(getExternalFilesDir(filePath), fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(myExtFile);
                    fos.write(fileContents.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WriteBox.setText("");
                Toast.makeText(MainActivity.this, "TextFile written on SD Card", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Input Field Empty", Toast.LENGTH_SHORT).show();
            }
        });
        read.setOnClickListener(view -> {
            FileReader fr;
            File myExtFile = new File(getExternalFilesDir(filePath), fileName);
            StringBuilder sb = new StringBuilder();
            String contents = "";
            try {
                fr = new FileReader(myExtFile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    contents += line + "\n";
                    line = br.readLine();
                }
                ReadBox.setText(contents);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        lat = (TextView) findViewById(R.id.lat);
        lon = (TextView) findViewById(R.id.lon);
        click = (Button) findViewById(R.id.gps);
        locMg = (LocationManager) getSystemService(LOCATION_SERVICE);
        lis = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String lat = "" + location.getLatitude();
                String lon = "" + location.getLongitude();
                WriteBox.setText("Latitude=" + lat + "\nLongitude=" + lon);
            }
        };
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        click.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locMg.requestLocationUpdates("gps", 5000, 0, lis);
        });
    }

    private boolean isExternalStorageAvailableRW() {
        String ExternalStorageState = Environment.getExternalStorageState();
        if(ExternalStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }
}

