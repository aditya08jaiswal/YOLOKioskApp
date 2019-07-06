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
import com.yolohealth.yolokioskapp.user.UserAttributes;

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
                ThisAppConfig.getInstance().putString(UserAttributes.QBUSERNAME,"yolo_atm100");
                ThisAppConfig.getInstance().putString(UserAttributes.QBPASSWORD,"y0l0adm1n");
                ThisAppConfig.getInstance().putLong(UserAttributes.QBCALLERID,45268036);
                ThisAppConfig.getInstance().putLong(UserAttributes.QBID,23434);
                ThisAppConfig.getInstance().putString(UserAttributes.AUTHKEY,"d8:9e:f3:77:ec:6a");
                ThisAppConfig.getInstance().putString(UserAttributes.AUTH_SECRET,"gAQt1bSJD6");
                ThisAppConfig.getInstance().putString(UserAttributes.QBAUTHKEY,"TWWeEdjpjgjRqsA");
                ThisAppConfig.getInstance().putString(UserAttributes.QBAUTH_SECRET,"DCkPTGCvnFNKwHn");
                ThisAppConfig.getInstance().putString(UserAttributes.KIOSKLOCATION,"Yolo-Baner");
                ThisAppConfig.getInstance().putString(UserAttributes.KIOSKID,"100");

                String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                ThisAppConfig.getInstance().putString(ThisAppConfig.DEVICEID,deviceId);
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}
