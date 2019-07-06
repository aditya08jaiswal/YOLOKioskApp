package com.yolohealth.yolokioskapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.yolohealth.yolokioskapp.R;
import com.yolohealth.yolokioskapp.helperclasses.ThisAppConfig;
import com.yolohealth.yolokioskapp.helperclasses.ThisAppInstallation;
import com.yolohealth.yolokioskapp.helperclasses.ThisUserConfig;
import com.yolohealth.yolokioskapp.platform.YoloUser;

public class MainActivity extends AppCompatActivity {
CardView consult_doctor,health_checkup,health_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final String userId = ThisUserConfig.getInstance().getString(ThisUserConfig.USER_ID);
        Tracker t = ((YoloUser) MainActivity.this.getApplication()).getTracker(
                YoloUser.TrackerName.APP_TRACKER);
        t.setScreenName("MainActivity");
        t.send(new HitBuilders.ScreenViewBuilder().build());
        consult_doctor=findViewById(R.id.consult_doctor);
        health_checkup=findViewById(R.id.health_checkup);
        health_history=findViewById(R.id.health_history);
        health_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uuid = ThisAppInstallation.id(getApplicationContext());
                ThisAppConfig.getInstance().putString(ThisAppConfig.APPUUID,uuid);
                ThisAppConfig.getInstance().putInt(ThisAppConfig.NOTIFICATION_SETTINGS,1);
                ThisAppConfig.getInstance().putInt(ThisAppConfig.SOUND_SETTINGS,1);
                String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                ThisAppConfig.getInstance().putString(ThisAppConfig.DEVICEID,deviceId);
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });

    }
}
