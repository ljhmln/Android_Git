package com.example.weather_20200509;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView content1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost host = findViewById(R.id.host);

        content1 = (TextView) findViewById(R.id.content1);

        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon1, null));
//        content1.setText("여기에");
        spec.setContent(R.layout.fragsun);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon2, null));

        spec.setContent(R.id.content2);
        host.addTab(spec);

        spec = host.newTabSpec("tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon3, null));

        spec.setContent(R.id.content3);
        host.addTab(spec);
    }





}
