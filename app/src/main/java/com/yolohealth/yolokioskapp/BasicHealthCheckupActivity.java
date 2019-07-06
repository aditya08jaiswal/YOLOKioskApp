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
        heightFrag.setBackground(getDrawable(R.drawable.menu_bg0_red));
        heightFrag.setTextColor(getColor(R.color.color_active_menu_text));

        heightFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HeightBHCFragment());

                heightFrag.setBackground(getDrawable(R.drawable.menu_bg0_red));
                heightFrag.setTextColor(getColor(R.color.color_active_menu_text));

                bodyStatsFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                bodyStatsFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                bloodPressureFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                bloodPressureFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                pulseTestFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                pulseTestFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                temperatureFrag.setBackground(getDrawable(R.drawable.menu_bg2));
                temperatureFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
            }
        });

        bodyStatsFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new BodyStatsBHCFragment());

                bodyStatsFrag.setBackground(getDrawable(R.drawable.menu_bg1_red));
                bodyStatsFrag.setTextColor(getColor(R.color.color_active_menu_text));

                heightFrag.setBackground(getDrawable(R.drawable.menu_bg0));
                heightFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                bloodPressureFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                bloodPressureFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                pulseTestFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                pulseTestFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                temperatureFrag.setBackground(getDrawable(R.drawable.menu_bg2));
                temperatureFrag.setTextColor(getColor(R.color.color_inactive_menu_text));

            }
        });

        bloodPressureFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new BloodPressureBHCFragment());

                bloodPressureFrag.setBackground(getDrawable(R.drawable.menu_bg1_red));
                bloodPressureFrag.setTextColor(getColor(R.color.color_active_menu_text));

                heightFrag.setBackground(getDrawable(R.drawable.menu_bg0));
                heightFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                bodyStatsFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                bodyStatsFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                pulseTestFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                pulseTestFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                temperatureFrag.setBackground(getDrawable(R.drawable.menu_bg2));
                temperatureFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
            }
        });

        pulseTestFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new PulseTestBHCFragment());
                pulseTestFrag.setBackground(getDrawable(R.drawable.menu_bg1_red));
                pulseTestFrag.setTextColor(getColor(R.color.color_active_menu_text));

                heightFrag.setBackground(getDrawable(R.drawable.menu_bg0));
                heightFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                bodyStatsFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                bodyStatsFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                bloodPressureFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                bloodPressureFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                temperatureFrag.setBackground(getDrawable(R.drawable.menu_bg2));
                temperatureFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
            }
        });

        temperatureFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new TemperatureBHCFragment());

                temperatureFrag.setBackground(getDrawable(R.drawable.menu_bg2_red));
                temperatureFrag.setTextColor(getColor(R.color.color_active_menu_text));

                heightFrag.setBackground(getDrawable(R.drawable.menu_bg0));
                heightFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                bodyStatsFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                bodyStatsFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                bloodPressureFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                bloodPressureFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
                pulseTestFrag.setBackground(getDrawable(R.drawable.menu_bg1));
                pulseTestFrag.setTextColor(getColor(R.color.color_inactive_menu_text));
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
