package com.yolohealth.yolokioskapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BasicHealthCheckupActivity extends AppCompatActivity {

    TextView heightFrag, bodyStatsFrag, bloodPressureFrag, pulseTestFrag, temperatureFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_health_checkup);

        heightFrag = findViewById(R.id.height_checkup_tv);
        bodyStatsFrag = findViewById(R.id.body_stats_checkup_tv);
        bloodPressureFrag = findViewById(R.id.blood_pressure_checkup_tv);
        pulseTestFrag = findViewById(R.id.pulse_test_checkup_tv);
        temperatureFrag = findViewById(R.id.temperature_checkup_tv);

        loadFragment(new HeightBHCFragment());

        heightFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HeightBHCFragment());
            }
        });

        bodyStatsFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new BodyStatsBHCFragment());
            }
        });

        bloodPressureFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new BloodPressureBHCFragment());
            }
        });

        pulseTestFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new PulseTextBHCFragment());
            }
        });

        temperatureFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new TemperatureBHCFragment());
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.bhc_frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
